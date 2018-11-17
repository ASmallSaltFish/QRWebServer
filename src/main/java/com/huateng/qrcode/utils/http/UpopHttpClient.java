package com.huateng.qrcode.utils.http;


import com.huateng.qrcode.utils.ConfigConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.Map;

/**
 * HttpClient通讯工具类
 *
 * @author Administrator
 */
public class UpopHttpClient {
    private static Logger logger = LoggerFactory.getLogger(UpopHttpClient.class);
    private URL url;
    private int connectionTimeout;
    private int readTimeOut;
    private String result;

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 初始化HttpClient相关关参数
     *
     * @param url               请求链接
     * @param connectionTimeout 请求超时时间
     * @param readTimeOut       响应超时时间
     */
    public UpopHttpClient(String url, int connectionTimeout, int readTimeOut) {
        try {
            logger.info("开始初始化服务器连接...");
            logger.debug("服务器地址：[ " + url + " ]");
            this.url = new URL(url);
            this.connectionTimeout = connectionTimeout;
            this.readTimeOut = readTimeOut;
            logger.info("初始化服务器连接完成...");
        } catch (MalformedURLException e) {
            logger.error("服务器连接异常,异常原因如下：" + e.getMessage(), e);
        }
    }

    /**
     * 发送http请求报文
     *
     * @param data     上送的报文
     * @param encoding 上送的编码
     * @return 响应状态码
     */
    public int send(Map<String, String> data, String encoding) throws Exception {
        try {
            logger.info("开始上送报文...");
            HttpURLConnection httpURLConnection = createConnection(encoding);
            if (null == httpURLConnection) {
                throw new Exception("创建连接失败！");
            }

            requestServer(httpURLConnection, getRequestParamString(data, encoding), encoding);
            this.result = response(httpURLConnection, encoding);
            return httpURLConnection.getResponseCode();
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * 发送请求
     */
    private void requestServer(URLConnection connection, String message, String encoder) throws Exception {
        logger.info("上送服务报文开始...");
        PrintStream out = null;
        try {
            connection.connect();
            out = new PrintStream(connection.getOutputStream(), false, encoder);
            out.print(message);
            out.flush();
            logger.info("上送服务报文完成...");
        } catch (Exception e) {
            logger.error("上送服务报文异常" + e.getMessage(), e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

    /**
     * 接收服务器响应测试
     */
    private String response(HttpURLConnection connection, String encoding) throws Exception {
        logger.info("接收服务器响应报文开始...");
        InputStream in = null;
        StringBuilder sb = new StringBuilder(1024);
        BufferedReader br = null;
        String temp = null;
        try {
            if (200 == connection.getResponseCode()) {
                in = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(in, encoding));
                while (null != (temp = br.readLine())) {
                    sb.append(temp);
                }
            } else {
                in = connection.getErrorStream();
                br = new BufferedReader(new InputStreamReader(in, encoding));
                while (null != (temp = br.readLine())) {
                    sb.append(temp);
                }
            }

            String responseStr = sb.toString();
            logger.debug("报文： [ " + responseStr + " ] ");
            logger.info("接收服务器响应报文完成");
            return responseStr;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != br) {
                br.close();
            }

            if (null != in) {
                in.close();
            }

            if (null != connection) {
                connection.disconnect();
            }
        }
    }

    /**
     * 创建连接
     *
     * @param encoding 编码
     */
    private HttpURLConnection createConnection(String encoding)
            throws ProtocolException {
        logger.info("服务器连接开始...");
        logger.info("服务器请求方式 [ POST ],编码 [ " + encoding + " ]");
        HttpURLConnection httpURLConnection = null;
        try {
            // 判断是否打开代理
            if ("true".equals(ConfigConstants.getParam("httpsRequestProxy"))) {
                logger.info("是否打开代理 [ 是 ]");
                logger.info("开始设置代理");
                Proxy proxy = setProxy();
                httpURLConnection = (HttpURLConnection) this.url.openConnection(proxy);
                logger.info("设置代理完成");
            } else {
                logger.info("是否打开代理 [ 否 ]");
                httpURLConnection = (HttpURLConnection) this.url.openConnection();
            }
        } catch (IOException e) {
            logger.error("服务器连接异常..." + e.getMessage(), e);
            return null;
        }

        httpURLConnection.setConnectTimeout(this.connectionTimeout);
        httpURLConnection.setReadTimeout(this.readTimeOut);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=" + encoding);
        httpURLConnection.setRequestMethod("POST");
        if ("https".equalsIgnoreCase(this.url.getProtocol())) {
            logger.info("请求方式[ https,POST ]");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
            httpsURLConnection.setHostnameVerifier(new BaseHttpSSLSocketFactory.TrustAnyHostnameVerifier());
            logger.info("服务器连接完成...");
            return httpsURLConnection;
        } else {
            logger.info("请求方式[ http,POST ]");
            logger.info("服务器连接完成...");
            return httpURLConnection;
        }
    }

    /**
     * 设置代理
     */
    private static Proxy setProxy() {
        String proxyIp = ConfigConstants.getParam("proxyIP");
        int proxyPort = Integer.parseInt(ConfigConstants.getParam("proxyPort"));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
        logger.info(" [http代理ip：" + proxyIp + " http代理端口:" + proxyPort + " ] ");
        return proxy;
    }

    /**
     * 组装报文
     */
    private String getRequestParamString(Map<String, String> requestParam, String coder) {
        logger.info("上送服务器报文组装开始... ");
        if ((null == coder) || ("".equals(coder))) {
            coder = "UTF-8";
        }

        StringBuilder sb = new StringBuilder();
        String queryString = "";
        if ((null != requestParam) && (0 != requestParam.size())) {
            for (Map.Entry<String, String> entry : requestParam.entrySet()) {
                try {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    sb.append(key).append("=").append(StringUtils.isBlank(value) ? "" : URLEncoder.encode(value, coder)).append("&");
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    return "";
                }
            }

            queryString = sb.substring(0, sb.length() - 1);
        }

        logger.debug("报文： [ " + queryString + " ] ");
        logger.info("上送服务器报文组装完成");
        return queryString;
    }
}
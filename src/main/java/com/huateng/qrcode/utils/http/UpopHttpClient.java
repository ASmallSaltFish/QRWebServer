package com.huateng.qrcode.utils.http;


import com.huateng.qrcode.utils.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 银联在线收单通讯
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
     * 初始化银联Http查询
     *
     * @param url
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
     * 发送银联在线收单报文
     *
     * @param data     上送的报文
     * @param encoding 上送的编码
     * @return 响应状态码
     * @throws Exception
     */
    public int send(Map<String, String> data, String encoding) throws Exception {
        try {
            logger.info("开始上送报文...");
            HttpURLConnection httpURLConnection = createConnection(encoding);
            if (null == httpURLConnection) {
                throw new Exception("创建联接失败");
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
            if (null != out)
                out.close();
            logger.info("上送服务报文完成...");
        } catch (Exception e) {
            logger.error("上送服务报文异常" + e.getMessage(), e);
        } finally {
            if (null != out)
                out.close();
        }
    }

    /**
     * 接收服务器响应测试
     *
     * @throws URISyntaxException
     * @throws IOException
     * @throws Exception
     */
    private String response(HttpURLConnection connection, String encoding) throws URISyntaxException, IOException, Exception {
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

            String str1 = sb.toString();
            logger.debug("报文： [ " + str1 + " ] ");
            logger.info("接收服务器响应报文完成");
            return str1;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != br) {
                br.close();
            }
            if (null != in) {
                in.close();
            }
            if (null != connection)
                connection.disconnect();
        }
    }

    /**
     * 创建连接
     *
     * @param encoding 编码
     * @throws ProtocolException
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
        httpURLConnection.setRequestProperty("Content-type", new StringBuilder().append("application/x-www-form-urlencoded;charset=").append(encoding).toString());
        httpURLConnection.setRequestMethod("POST");
        if ("https".equalsIgnoreCase(this.url.getProtocol())) {
            logger.info("请求方式[ https,POST ]");
            HttpsURLConnection husn = (HttpsURLConnection) httpURLConnection;
            husn.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
            husn.setHostnameVerifier(new BaseHttpSSLSocketFactory.TrustAnyHostnameVerifier());
            logger.info("服务器连接完成...");
            return husn;
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
        logger.info("银联在线收单代理 [ip：" + proxyIp + " http代理端口:" + proxyPort + " ] ");
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
        StringBuffer sf = new StringBuffer("");
        String reqstr = "";
        if ((null != requestParam) && (0 != requestParam.size())) {
            for (Map.Entry en : requestParam.entrySet()) {
                try {
                    sf.append(new StringBuilder()
                            .append((String) en.getKey())
                            .append("=")
                            .append((null == en.getValue())
                                    || ("".equals(en.getValue())) ? ""
                                    : URLEncoder.encode((String) en.getValue(),
                                    coder)).append("&").toString());
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.getMessage(), e);
                    return "";
                }
            }
            reqstr = sf.substring(0, sf.length() - 1);
        }
        logger.debug("报文： [ " + reqstr + " ] ");
        logger.info("上送服务器报文组装完成");
        return reqstr;
    }

    /**
     * 字符转MAP
     *
     * @param res
     * @return
     */
    public static Map<String, String> convertResultString2Map(String res) {
        Map map = null;
        if ((null != res) && (!"".equals(res.trim()))) {
            String[] resArray = res.split("&");
            if (0 != resArray.length) {
                map = new HashMap(resArray.length);
                for (String arrayStr : resArray) {
                    if ((null == arrayStr) || ("".equals(arrayStr.trim()))) {
                        continue;
                    }
                    int index = arrayStr.indexOf("=");
                    if (-1 == index) {
                        continue;
                    }
                    map.put(arrayStr.substring(0, index), arrayStr.substring(index + 1));
                }
            }
        }
        logger.info("服务器报文转换完成...");
        return map;
    }

    private static void convertResultStringJoinMap(String res,
                                                   Map<String, String> map) {
        if ((null != res) && (!"".equals(res.trim()))) {
            String[] resArray = res.split("&");
            if (0 != resArray.length)
                for (String arrayStr : resArray) {
                    if ((null == arrayStr) || ("".equals(arrayStr.trim()))) {
                        continue;
                    }
                    int index = arrayStr.indexOf("=");
                    if (-1 == index) {
                        continue;
                    }
                    map.put(arrayStr.substring(0, index),
                            arrayStr.substring(index + 1));
                }
        }
    }

    /**
     * 字符转Map
     *
     * @param result
     * @return
     */
    public static Map<String, String> convertResultStringToMap(String result) {
        logger.info("服务器报文转换开始...");
        if (result.contains("{")) {
            String separator = "\\{";
            String[] res = result.split(separator);

            Map map = new HashMap();

            convertResultStringJoinMap(res[0], map);

            for (int i = 1; i < res.length; i++) {
                int index = res[i].indexOf("}");

                String specialValue = new StringBuilder().append("{")
                        .append(res[i].substring(0, index)).append("}")
                        .toString();

                int indexKey = res[(i - 1)].lastIndexOf("&");
                String specialKey = res[(i - 1)].substring(indexKey + 1,
                        res[(i - 1)].length() - 1);

                map.put(specialKey, specialValue);

                String normalResult = res[i].substring(index + 2, res[i].length());

                convertResultStringJoinMap(normalResult, map);
            }
            logger.info("服务器报文转换完成...");
            return map;
        }

        return convertResultString2Map(result);
    }
}
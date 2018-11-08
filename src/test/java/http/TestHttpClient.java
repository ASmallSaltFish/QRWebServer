package http;

import com.huateng.qrcode.utils.ConfigConstants;
import com.huateng.qrcode.utils.http.UpopHttpClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试HttpClient通讯
 */
public class TestHttpClient {

    /**
     * 测试：UponHttpClient工具类，发送post请求，获取返回的响应
     */
    @Test
    public void testSend() throws Exception {
        String requestUrl = ConfigConstants.getParam("requestUrl");
        UpopHttpClient httpClient = new UpopHttpClient(requestUrl, 10000, 10000);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userName", "zhangsan");
        paramMap.put("userId", "1111");
        int responseCode = httpClient.send(paramMap, "utf-8");
        System.out.println("==>>responseCode=" + responseCode);
        String result = httpClient.getResult();
        System.out.println("result=" + result);
    }
}

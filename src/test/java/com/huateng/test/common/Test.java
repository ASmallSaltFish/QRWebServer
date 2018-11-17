package com.huateng.test.common;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void test() throws UnsupportedEncodingException {
        Map<String, String> paramMap = new HashMap<>();
        String value = URLEncoder.encode("zhangsan%", "utf-8");
        System.out.println("value=" + value);
        paramMap.put("name", "zhangsan%");
        paramMap.put("age", "");
        paramMap.put("sex", null);
        System.out.println(getRequestParamString(paramMap, "utf-8"));
    }

    /**
     * 组装报文
     */
    private String getRequestParamString(Map<String, String> requestParam, String coder) {
        if ((null == coder) || ("".equals(coder))) {
            coder = "UTF-8";
        }

        StringBuilder sb = new StringBuilder("");
        String queryString = "";
        if ((null != requestParam) && (0 != requestParam.size())) {
            for (Map.Entry<String, String> entry : requestParam.entrySet()) {
                try {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    sb.append(key).append("=").append(StringUtils.isBlank(value) ? "" : URLEncoder.encode(value, coder)).append("&");
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
            }

            queryString = sb.substring(0, sb.length() - 1);
        }

        return queryString;
    }
}

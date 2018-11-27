package com.huateng.qrcode.controller.base;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.common.constants.Constants;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有Controller类继承的控制类的基类
 *
 * @author qinyupeng
 * @since 2018-11-13 15:59:39
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * http请求参数校验，可以根据具体业务，子类自己实现个性化校验
     */
    protected Map<String, String> checkAndGetParamMap(Map<String, String[]> parameterMap) {
        if (parameterMap.get(Constants.REQ_PARAM_QR_CODE) == null) {
            logger.error("http请求报文参数qrCode不能为空！");
            throw new RuntimeException("http报文请求参数qrCode不能为空！");
        }


        Map<String, String> paramMap = new HashMap<>(parameterMap.size());
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String parameterName = entry.getKey();
            String[] paramValues = entry.getValue();
            if (paramValues == null || paramValues.length != 1) {
                logger.error(String.format("接收报文参数%s值为空或者参数值不唯一！", parameterName));
                throw new RuntimeException("http报文参数校验错误！");
            }

            if ("qrCode".equals(parameterName) && paramValues[0].length() != 34) {
                logger.error("http请求报文参数qrCode参数值长度错误，长度为: " + paramValues[0].length());
                throw new RuntimeException("http请求报文参数qrCode参数值长度有误！");
            }

            paramMap.put(parameterName, paramValues[0]);
        }

        return paramMap;
    }

    /**
     * 根据http请求头中user-agent参数，判断请求来源app
     *
     * @param request  HttpServletRequest对象
     * @param paramMap 参数集合
     */
    protected void setUserAgent(HttpServletRequest request, Map<String, String> paramMap) {
        String userAgent = request.getHeader("user-agent");
        if (StringUtils.isBlank(userAgent)) {
            return;
        }

        if (userAgent.contains("AlipayClient")) {
            paramMap.put(Constants.HTTP_REQ_SYS, Constants.HTTP_REQ_SYS_ALIPAY);
        } else if (userAgent.contains("MicroMessenger")) {
            paramMap.put(Constants.HTTP_REQ_SYS, Constants.HTTP_REQ_SYS_WX);
        }
    }


    /**
     * 返回json格式字符串
     */
    protected String renderJson(ResponseVo responseVo) {

        return JSON.toJSONString(responseVo);
    }

}

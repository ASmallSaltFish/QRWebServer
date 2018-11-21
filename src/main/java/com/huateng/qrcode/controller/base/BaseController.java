package com.huateng.qrcode.controller.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        //todo 根据实际业务，校验http请求参数
        Map<String, String> paramMap = new HashMap<>(parameterMap.size());
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String parameterName = entry.getKey();
            String[] paramValues = entry.getValue();
            if (paramValues == null || paramValues.length != 1) {
                logger.error("接收报文参数" + parameterName + "值为空或者参数值不唯一！");
                throw new RuntimeException("http报文参数校验错误！");
            }

            paramMap.put(parameterName, paramValues[0]);
        }

        return paramMap;
    }


    /**
     * 返回json格式字符串
     */
    protected String renderJson(ResponseVo responseVo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(responseVo);
        } catch (JsonProcessingException e) {
            logger.error("将responseVo对象转化为json字符串时出现异常", e);
        }

        return null;
    }

}

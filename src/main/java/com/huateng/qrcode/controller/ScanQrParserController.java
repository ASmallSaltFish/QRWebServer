package com.huateng.qrcode.controller;

import com.huateng.qrcode.utils.SpringContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码扫码类解析（提供http请求服务）
 */
@Controller
@RequestMapping(value = "/scan")
public class ScanQrParserController {

    private ApplicationContext ctx = SpringContextUtil.getInstance().getApplicationContext();

    @RequestMapping(value = "/handler")
    @ResponseBody
    public String handlerScanTask(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        String serviceCode = request.getParameter("serviceCode");

        paramMap.put("serviceCode", serviceCode);
        return null;
    }
}

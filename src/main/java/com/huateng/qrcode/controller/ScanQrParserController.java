package com.huateng.qrcode.controller;

import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.controller.base.BaseController;
import com.huateng.qrcode.service.httpserver.ScanQrParserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 二维码扫码类解析（提供http请求服务）
 */
@Controller
@RequestMapping(value = "/scan")
public class ScanQrParserController extends BaseController {

    @Autowired
    private ScanQrParserService scanQrParserService;

    @RequestMapping(value = "/handler")
    @ResponseBody
    public String handlerScanTask(HttpServletRequest request) {
        System.out.println(scanQrParserService);
        ResponseVo responseVo = new ResponseVo();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isEmpty(parameterMap)) {
            logger.error("http请求参数paramMap为空!");
            //返回处理失败
            responseVo.getBusRespBody().setProcessCode(ErrorCodeEnum.FAIL.getCode());
            return renderJson(responseVo);
        }

        //校验http请求业务参数，将参数值封装为map集合
        Map<String, String> paramMap = null;
        try {
            paramMap = checkAndGetParamMap(parameterMap);
        } catch (Exception e) {
            logger.error("http报文参数校验不通过", e.getMessage());
            responseVo.getBusRespBody().setProcessCode(ErrorCodeEnum.FAIL.getCode());
            return renderJson(responseVo);
        }

        try {
            responseVo = scanQrParserService.handler(paramMap);
            //todo 确定返回结果的类型
            return renderJson(responseVo);
        } catch (Exception e) {
            logger.error("业务类处理出现异常", e);
            responseVo.getBusRespBody().setProcessCode(ErrorCodeEnum.FAIL.getCode());
            return renderJson(responseVo);
        }
    }
}

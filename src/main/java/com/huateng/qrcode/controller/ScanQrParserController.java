package com.huateng.qrcode.controller;

import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.controller.base.BaseController;
import com.huateng.qrcode.service.httpserver.ScanQrParserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 二维码扫码类解析（提供http请求服务）
 */
@Controller
@RequestMapping(value = "/scan")
public class ScanQrParserController extends BaseController {

    @Resource
    private ScanQrParserService scanQrParserService;

    @RequestMapping(value = "/handler", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String handlerScanTask(HttpServletRequest request) {
        System.out.println(scanQrParserService);
        ResponseVo responseVo = new ResponseVo();
        BusRespBody busRespBody = new BusRespBody();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isEmpty(parameterMap)) {
            logger.error("http请求参数paramMap为空!");
            //返回处理失败
            busRespBody.setProcessCode(ErrorCodeEnum.FAIL.getCode());
            busRespBody.setProcessStatus(ErrorCodeEnum.FAIL.name());
            busRespBody.setMsg("http请求参数为空！");
            responseVo.setBusRespBody(busRespBody);
            return renderJson(responseVo);
        }

        //校验http请求业务参数，将参数值封装为map集合
        Map<String, String> paramMap = null;
        try {
            paramMap = checkAndGetParamMap(parameterMap);
        } catch (Exception e) {
            logger.error("http报文参数校验不通过", e.getMessage());
            busRespBody.setProcessCode(ErrorCodeEnum.FAIL.getCode());
            busRespBody.setProcessStatus(ErrorCodeEnum.FAIL.name());
            busRespBody.setMsg("http报文参数校验不通过！");
            responseVo.setBusRespBody(busRespBody);
            return renderJson(responseVo);
        }

        try {
            responseVo = scanQrParserService.handler(paramMap);
            //todo 确定返回结果的类型
            logger.info("返回报文信息：" + renderJson(responseVo));
            return renderJson(responseVo);
        } catch (Exception e) {
            logger.error("业务类处理出现异常", e);
            busRespBody.setProcessCode(ErrorCodeEnum.FAIL.getCode());
            busRespBody.setProcessStatus(ErrorCodeEnum.FAIL.name());
            busRespBody.setMsg(e.getMessage());
            responseVo.setBusRespBody(busRespBody);
            logger.info("返回报文信息：" + renderJson(responseVo));
            return renderJson(responseVo);
        }
    }
}

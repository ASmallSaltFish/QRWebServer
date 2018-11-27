package com.huateng.qrcode.controller;

import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.controller.base.BaseController;
import com.huateng.qrcode.service.httpserver.ScanQrParserService;
import com.huateng.qrcode.utils.DateUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
        logger.info("接收到http扫码请求开始。。。");
        Date date = new Date();

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
            //存储接收请求的日期和时间
            paramMap.put(Constants.RECEIVE_DATE, DateUtil.formatDate(date));
            paramMap.put(Constants.RECEIVE_TIME, DateUtil.formatTime(date));
            //设置请求客户端参数（微信、支付宝）
            setUserAgent(request, paramMap);
        } catch (Exception e) {
            logger.error("http报文参数校验不通过", e.getMessage());
            busRespBody.setProcessCode(ErrorCodeEnum.FAIL.getCode());
            busRespBody.setProcessStatus(ErrorCodeEnum.FAIL.name());
            busRespBody.setMsg("http报文参数校验不通过！");
            responseVo.setBusRespBody(busRespBody);
            return renderJson(responseVo);
        }

        try {
            //实际解析服务接口
            responseVo = scanQrParserService.handler(paramMap);
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

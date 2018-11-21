package com.huateng.qrcode.qrserver.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.base.AppParamHeader;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.SysParamHeader;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "DistingQrGenerateImpl")
public class DistingQrGenerateImpl implements QrServerManager {

    private static final Logger logger = LoggerFactory.getLogger(DistingQrGenerateImpl.class);

    //todo 实际处理业务操作的方法
    @Override
    public String handler(RequestVo requestVo) {

        logger.info("识别类二维码生成服务，入参：" + requestVo.toString());
        //定义参数
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            //获取系统层数据

            //获取应用层数据
            String scene = requestVo.getAppHeader().getScene();
            String reqsys = requestVo.getAppHeader().getReqSys();
            String usetype = requestVo.getAppHeader().getUseType();
            String actionscope = requestVo.getAppHeader().getActionScope();
            String ewmmsgid = requestVo.getAppHeader().getEwmMsgId();
            String ewmversion = requestVo.getAppHeader().getEwmVersion();
            String generationmode = requestVo.getAppHeader().getGenerationMode();
            String industryapp = requestVo.getAppHeader().getIndustryApp();
            String readmode = requestVo.getAppHeader().getReadMode();
            String rspsys = requestVo.getAppHeader().getRspSys();
            //获取业务数据
            String paramMap = objectMapper.writeValueAsString(requestVo.getBusBody().getParamMap());

            //TODO 校验参数空
            //TODO 检查生成规则是否存在
            //TODO 请求者合法性查检
            //TODO 应用场景查检以及验证是否有效
            //TODO 生成校验位
            //TODO 将数据token化
            //TODO 根据风险等级调用加密算法
            //TODO 动静态码的时效配制
            //TODO 将二维码信息和业务数据入库
            //TODO 添加交易流水
            //TODO 出参日志
            //TODO 返回二维码
        } catch (Exception e) {
            //TODO 添加交易流水
            e.printStackTrace();
        }
        return null;
    }

}

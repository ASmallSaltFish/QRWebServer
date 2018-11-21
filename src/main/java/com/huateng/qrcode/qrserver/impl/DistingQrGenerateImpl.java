package com.huateng.qrcode.qrserver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 识别类二维码生成服务
 *
 * @author xuzhangsheng
 * @since 2018-11-20
 */
@Service(value = "DistingQrGenerateImpl")
public class DistingQrGenerateImpl implements QrServerManager {

    private static final Logger logger = LoggerFactory.getLogger(DistingQrGenerateImpl.class);

    /**
     * 生成识别类二维码
     *
     * @param in
     * @return out
     */
    @Override
    public ResponseVo handler(RequestVo in) {

        logger.info("识别类二维码生成服务，入参：" + in.toString());
        //定义参数
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseVo out = new ResponseVo();

        try {

            //获取系统层数据

            //获取应用层数据
            String scene = in.getAppHeader().getScene();
            String reqsys = in.getAppHeader().getReqSys();
            String usetype = in.getAppHeader().getUseType();
            String actionscope = in.getAppHeader().getActionScope();
            String ewmmsgid = in.getAppHeader().getEwmMsgId();
            String ewmversion = in.getAppHeader().getEwmVersion();
            String generationmode = in.getAppHeader().getGenerationMode();
            String industryapp = in.getAppHeader().getIndustryApp();
            String readmode = in.getAppHeader().getReadMode();
            String rspsys = in.getAppHeader().getRspSys();
            //获取业务数据
            String paramMap = objectMapper.writeValueAsString(in.getBusBody().getParamMap());

            //TODO 校验参数空
            //TODO 检查生成规则是否存在(拼成ID查询)
            //TODO 请求者合法性查检(黑名单)
            //TODO 应用场景查检以及验证是否有效
            //TODO 生成序列号（需考虑）
            //TODO 生成校验位(待定)
            //TODO 将数据token化（场景、行业应用、序列号、校验位）
            //TODO 根据风险等级调用加密算法（可能不做）
            //TODO 动静态码的时效配制
            //TODO 将二维码信息和业务数据入库
            //TODO 添加交易流水
            //TODO 出参日志
            //TODO 返回二维码
        } catch (Exception e) {
            //TODO 添加交易流水
            e.printStackTrace();
        }
        return out;
    }

}

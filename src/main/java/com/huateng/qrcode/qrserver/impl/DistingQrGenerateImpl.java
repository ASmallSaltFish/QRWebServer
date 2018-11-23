package com.huateng.qrcode.qrserver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.service.algorithm.TokenService;
import com.huateng.qrcode.utils.QrUtil;
import com.huateng.qrcode.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @class: DistingQrGenerateImpl
 * @title: 识别类二维码生成服务
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
@Service(value = "DistingQrGenerateImpl")
public class DistingQrGenerateImpl implements QrServerManager {

    private static final Logger logger = LoggerFactory.getLogger(DistingQrGenerateImpl.class);

    @Autowired
    private TokenService tokenService;

    /**
     * 生成识别类二维码
     *
     * @param in
     * @return out
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @Override
    public ResponseVo handler(RequestVo in) {

        logger.info("识别类二维码生成服务开始，入参：" + in.toString());
        //定义参数
        ObjectMapper objectMapper = new ObjectMapper();
        //TODO 常量 保留位
        String reserve = "0";
        String ewmData = null;
        ResponseVo out = new ResponseVo();


        try {

            //获取系统层数据

            //获取应用层数据
            //String ewmmsgid = in.getAppHeader().getEwmMsgId();
            //String readmode = in.getAppHeader().getReadMode();
            String scene = in.getAppHeader().getScene();
            String reqsys = in.getAppHeader().getReqSys();
            String usetype = in.getAppHeader().getUseType();
            String actionscope = in.getAppHeader().getActionScope();
            String ewmversion = in.getAppHeader().getEwmVersion();
            String generationmode = in.getAppHeader().getGenerationMode();
            String industryapp = in.getAppHeader().getIndustryApp();
            String prescription = in.getAppHeader().getPrescription();
            String rspsys = in.getAppHeader().getRspSys();
            //获取业务数据
            String paramMap = objectMapper.writeValueAsString(in.getBusBody().getParamMap());

            // 校验参数空
            if(StringUtil.hasEmptyMultipleStr(ewmversion, generationmode, prescription,
                    actionscope, reqsys, industryapp, usetype, scene)){
                logger.error("字段为空！");
                out.setResultCode(ErrorCodeEnum.E100000.getCode());
                out.setResultMsg(ErrorCodeEnum.E100000.getDesc());
                return out;
            }
            //TODO 检查生成规则是否存在(拼成ID查询)
            //二维码模板= 二维码版本 + 生成方式 + 时效 + 类别域 + 保留位 + 请求系统 + 行业应用 + 用途 + 场景
            String templet = ewmversion + generationmode + prescription + actionscope
                    + reserve + reqsys + industryapp + usetype + scene;

            //TODO 请求者合法性查检(黑名单) 应用场景查检以及验证是否有效

            //TODO 生成序列号（待添加）
            // 生成校验位(待定33位)
            String ewmDataBinaryString = StringUtil.toBinaryString(ewmData);
            String checkBit = QrUtil.getCheckFlag(ewmDataBinaryString);
            // 将数据token化（场景、行业应用、序列号、校验位）
            String token = tokenService.getToken();
            //TODO 根据风险等级调用加密算法（暂无）
            //TODO 动静态码的时效配制
            //TODO 将二维码信息和业务数据入库
            //TODO 添加交易流水
            logger.info("识别类二维码生成服务结束，出参：" + out.toString());
            //TODO 生成二维码图片（查看包是否支持多种形式二维码）
            //TODO 返回二维码
        } catch (Exception e) {
            //TODO 添加交易流水
            logger.info("识别类二维码生成失败");
            e.printStackTrace();
        }
        return out;
    }

}

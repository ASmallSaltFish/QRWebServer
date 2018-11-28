package com.huateng.qrcode.qrserver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.common.enums.ExpiryStatusEnum;
import com.huateng.qrcode.common.model.DisplayQrcode;
import com.huateng.qrcode.common.model.QrModule;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.service.algorithm.TokenService;
import com.huateng.qrcode.service.form.DisplayQrcodeService;
import com.huateng.qrcode.service.form.QrModuleService;
import com.huateng.qrcode.utils.DateUtil;
import com.huateng.qrcode.utils.QrUtil;
import com.huateng.qrcode.utils.SeqGeneratorUtil;
import com.huateng.qrcode.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @class: DisplayQrGenerateImpl
 * @title: 识别类二维码生成服务
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
@Service(value = "DisplayQrGenerateImpl")
public class DisplayQrGenerateImpl implements QrServerManager {

    private static final Logger logger = LoggerFactory.getLogger(DisplayQrGenerateImpl.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private QrModuleService qrModuleService;

    @Autowired
    private DisplayQrcodeService displayQrcodeService;

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
        ResponseVo out = new ResponseVo();
        //定义参数
        ObjectMapper objectMapper = new ObjectMapper();
        //保留位
        String reserve = Constants.RESERVE;
        //二维码明文
        String ewmData = null;
        //二维码密文
        String ewmDataCipher = null;
        //获取当前日期（yyyyMMdd格式）
        String currentDate = DateUtil.formatCurrentDate();
        //获取当前日期（yyMMdd格式）
        String ewmCurrentDate = currentDate.substring(2);
        //获取当前日期和时间（yyyyMMddHHmmss格式）
        String currentTime = DateUtil.formatCurrentDateTime();
        try {
            //获取系统层数据

            //获取应用层数据
            //String ewmmsgid = in.getAppHeader().getEwmMsgId();
            //String readmode = in.getAppHeader().getReadMode();

            //场景
            String scene = in.getAppHeader().getScene();
            //请求系统
            String reqSys = in.getAppHeader().getReqSys();
            //用途
            String useType = in.getAppHeader().getUseType();
            //类别域
            String actionScope = in.getAppHeader().getActionScope();
            //二维码版本
            String ewmVersion = in.getAppHeader().getEwmVersion();
            //生成方式
            String generationMode = in.getAppHeader().getGenerationMode();
            //行业应用
            String industryApp = in.getAppHeader().getIndustryApp();
            //时效
            String prescription = in.getAppHeader().getPrescription();
            //应答系统
            String rspsys = in.getAppHeader().getRspSys();
            //获取业务数据
            String paramMap = objectMapper.writeValueAsString(in.getBusBody().getParamMap());

            // 校验参数空
            if (StringUtil.hasEmptyMultipleStr(ewmVersion, generationMode, prescription,
                    actionScope, reqSys, industryApp, useType, scene)) {
                logger.error("字段为空！");
                out.setResultCode(ErrorCodeEnum.E100000.getCode());
                out.setResultMsg(ErrorCodeEnum.E100000.getDesc());
                return out;
            }
            //二维码模板 = 二维码版本 + 生成方式 + 时效 + 类别域 + 保留位 + 请求系统 + 行业应用 + 用途 + 场景
            String templet = ewmVersion + generationMode + prescription + actionScope
                    + reserve + reqSys + industryApp + useType + scene;

            // 检查生成规则是否存在(拼成ID查询)
            /*QrModule qrModule = qrModuleService.selectById(templet);
            if (qrModule == null) {
                logger.error("生成规则不存在！");
                out.setResultCode(ErrorCodeEnum.E200000.getCode());
                out.setResultMsg(ErrorCodeEnum.E200000.getDesc());
                return out;
            }*/
            QrModule qrModule = new QrModule();
            qrModule.setEncodeMode("1");
            qrModule.setReadMode("2");
            qrModule.setRiskLevel("3");
            qrModule.setEntryFlag("4");
            qrModule.setKeyVersion("5");
            qrModule.setExpiryDate("10");
            //编码原理
            String encodeMode = qrModule.getEncodeMode();
            //读取方式
            String readMode = qrModule.getReadMode();
            //风险等级
            String riskLevel = qrModule.getRiskLevel();
            //加密标识
            String entryFlag = qrModule.getEntryFlag();
            //密钥版本
            String keyVersion = qrModule.getKeyVersion();
            //失效时间（单位秒）
            String expiryDate = qrModule.getExpiryDate();
            //有效时间
            String effectiveTime = DateUtil.dateFormat(
                    DateUtil.secondAdd(null, Integer.valueOf(expiryDate)), Constants.DATE_TIME_PATTERN_DEFAULT);

            //TODO 请求者合法性查检(黑名单) 应用场景查检以及验证是否有效

            // 生成序列号
            String seq = SeqGeneratorUtil.getInstance().getSequenceNo(Constants.SEQ_KEY);

            /**
             * 二维码明文 = 二维码版本号 + 编码方式 + 生成方式 + 时效 + 类别域 + 读取方式 + 风险等级 + 保留位
             *          + 加密标识 + 密钥版本 + 请求系统 + 行业应用 + 用途 + 场景 + 当前日期 + 序列号
             */
            ewmData = ewmVersion + encodeMode + generationMode + prescription + actionScope
                    + readMode + riskLevel + reserve + entryFlag + keyVersion + reqSys
                    + industryApp + useType + scene + ewmCurrentDate + seq;


            // 生成校验位(待定33位)
            String ewmDataBinaryString = StringUtil.toBinaryString(ewmData);
            String checkBit = QrUtil.getCheckFlag(ewmDataBinaryString);

            // 获取13位Token数据
            String token = tokenService.getToken();
            //需要Token字段 行业 + 场景 + 序列号 + 校验位
            String beforeToken = industryApp + scene + seq + checkBit;

            // 将数据token化（场景、行业应用、序列号、校验位）
            //token行业应用
            String industryAppToken = token.substring(0, 3);
            //token场景
            String sceneToken = token.substring(3, 5);
            //token序列号
            String seqToken = token.substring(5, 12);
            //token校验位
            String checkBitToken = token.substring(12, 13);
            System.out.println("industryAppToken:" + industryAppToken + ",sceneToken:" + sceneToken
                    + ",seqToken:" + seqToken + ",checkBitToken:" + checkBitToken);
            //二维码明文 = 二维码明文 + 校验位
            ewmData = ewmData + checkBit;
            System.out.println("明文:" + ewmData);
            /**
             * 二维码密文 = 二维码版本号 + 编码方式 + 生成方式 + 时效 + 类别域 + 读取方式 + 风险等级 + 保留位
             *         + 加密标识 + 密钥版本 + 请求系统 + Token行业应用 + 用途 + Token场景 + 当前日期 + Token序列号
             *         + Token校验位
             */
            ewmDataCipher = ewmVersion + encodeMode + generationMode + prescription + actionScope
                    + readMode + riskLevel + reserve + entryFlag + keyVersion + reqSys
                    + industryAppToken + useType + sceneToken + ewmCurrentDate + seqToken + checkBitToken;
            System.out.println("密文:" + ewmDataCipher);
            logger.info("mingwen:" + ewmData);
            logger.info("miwen:" + ewmDataCipher);
            //TODO 根据风险等级调用加密算法（暂无）
            //TODO 动静态码的时效配制
            //TODO 将二维码信息和业务数据入库
            DisplayQrcode displayQrcode = new DisplayQrcode();
            displayQrcode.setQrcodeId(ewmData);
            displayQrcode.setTempletId(qrModule.getQrModId());
            displayQrcode.setCustomModDate(currentDate);
            displayQrcode.setCustomModTime(currentTime.substring(8));
            displayQrcode.setToken(token);
            displayQrcode.setBeforeToken(beforeToken);
            displayQrcode.setExpiryDateTime(effectiveTime);
            displayQrcode.setExpiryStatus(ExpiryStatusEnum.EFFECTIVE.getCode());
            //
            displayQrcode.setPictureId("");
            displayQrcode.setCrtDate(currentDate);
            displayQrcode.setCrtTime(currentTime.substring(8));
            //
            displayQrcode.setCrtUser("");
            displayQrcode.setData(paramMap);
            //
            displayQrcode.setOrgId("");
            //
            displayQrcode.setQrUrl("");
            //
            displayQrcode.setUpdateUser("");
            displayQrcode.setUpdDate(currentDate);
            displayQrcodeService.insert(displayQrcode);
            //TODO 添加交易流水
            //TODO 生成二维码图片（查看包是否支持多种形式二维码）
            logger.info("识别类二维码生成服务结束，出参：" + out.toString());
            out.setResultCode(ErrorCodeEnum.SUCCESS.getCode());
            out.setResultMsg(ErrorCodeEnum.SUCCESS.getDesc());
        } catch (Exception e) {
            //TODO 添加交易流水
            logger.info("识别类二维码生成失败");
            out.setResultCode(ErrorCodeEnum.FAIL.getCode());
            out.setResultMsg(ErrorCodeEnum.FAIL.getDesc());
            e.printStackTrace();
        }
        return out;
    }

}

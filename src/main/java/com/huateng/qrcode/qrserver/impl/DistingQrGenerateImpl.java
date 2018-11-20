package com.huateng.qrcode.qrserver.impl;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.qrserver.QrServerManager;

public class DistingQrGenerateImpl implements QrServerManager {

    //todo 实际处理业务操作的方法
    @Override
    public String handler(RequestVo requestVo) {
        //TODO 入参日志
        //TODO 获取参数
        //TODO 校验参数空（是否需要）
        String scene = requestVo.getAppHeader().getScene();
        //TODO 检查生成规则是否存在、合法
        //TODO 请求者合法性查检
        //TODO 应用场景查检以及验证是否有效
        //TODO 调用Token产生随机数
        //TODO 根据作用域计算风险等级
        //TODO 根据风险等级触发调度校验位算法、加密算法
        //TODO 动静态码的时效配制
        //TODO 将二维码信息和预约挂号链接数据入库
        //TODO 出参日志
        return null;
    }
}

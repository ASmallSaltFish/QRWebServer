package com.huateng.qrcode.service;

import com.huateng.qrcode.common.exception.QrcException;

/**
 * @class: QrcodeTxnLogService
 * @title: 操作交易流水服务
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
public interface QrcodeTxnLogService {
    /**
     * 添加二维码交易流水
     *
     * @throws QrcException
     */
    public void addQrcodeTxnlog() throws QrcException;
}

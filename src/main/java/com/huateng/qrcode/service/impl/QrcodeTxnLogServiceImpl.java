package com.huateng.qrcode.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.common.exception.QrcException;
import com.huateng.qrcode.service.QrcodeTxnLogService;
import org.springframework.stereotype.Service;

/**
 * @class: QrcodeTxnLogServiceImpl
 * @title: 操作交易流水实现类
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
@Service(value = "QrcodeTxnLogServiceImpl")
public class QrcodeTxnLogServiceImpl implements QrcodeTxnLogService {

    /**
     * 添加交易流水
     *
     * @throws QrcException
     */
    @Override
    public void addQrcodeTxnlog() throws QrcException {
        IdWorker.getIdStr();

    }
}

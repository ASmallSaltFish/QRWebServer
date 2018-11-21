package com.huateng.qrcode.service.algorithm;

import com.huateng.qrcode.common.exception.QrcException;

/**
 * @class: MovePositionService
 * @title: 移位算法服务
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
public interface MovePositionService {

    /**
     * 移位算法
     * @param ewmData
     * @throws QrcException
     */
    public void movePosition(String ewmData) throws QrcException;
}

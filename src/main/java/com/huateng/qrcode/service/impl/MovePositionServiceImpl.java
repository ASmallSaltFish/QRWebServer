package com.huateng.qrcode.service.impl;

import com.huateng.qrcode.common.exception.QrcException;
import com.huateng.qrcode.service.MovePositionService;
import org.springframework.stereotype.Service;

/**
 * @class: MovePositionServiceImpl
 * @title: 移位算发实现类
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
@Service(value = "MovePositionServiceImpl")
public class MovePositionServiceImpl implements MovePositionService {

    /**
     * 移位算法
     *
     * @param ewmData
     * @throws QrcException
     */
    @Override
    public void movePosition(String ewmData) throws QrcException {

    }
}

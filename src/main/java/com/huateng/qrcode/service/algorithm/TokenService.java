package com.huateng.qrcode.service.algorithm;

import com.huateng.qrcode.common.exception.QrcException;

/**
 * @class: TokenService
 * @title: Token服务
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
public interface TokenService {
    /**
     * 获取token
     *
     * @return
     * @throws QrcException
     */
    public String getToken() throws QrcException;

}

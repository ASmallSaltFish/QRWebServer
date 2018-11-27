package com.huateng.qrcode.service.form;

import com.baomidou.mybatisplus.service.IService;
import com.huateng.qrcode.common.model.IdentityQrcode;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
public interface IdentityQrcodeService extends IService<IdentityQrcode> {

    /**
     * 根据token，获取唯一的二维码记录
     *
     * @param token 二维码token化后的token值
     * @return 返回二维码实体对象
     */
    IdentityQrcode findByToken(String token);
}

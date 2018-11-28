package com.huateng.qrcode.service.form;

import com.baomidou.mybatisplus.service.IService;
import com.huateng.qrcode.common.model.IdentityQrcode;

/**
 * <p>
 * 识别类二维码服务类
 * </p>
 *
 * @author qinyupeng
 * @since 2018-11-28 10:21:10
 */
public interface IdentityQrcodeService extends IService<IdentityQrcode> {

    /**
     * 根据token，获取唯一的二维码记录
     *
     * @param token 二维码token化后的token值
     * @return 返回二维码实体对象
     */
    IdentityQrcode findByToken(String token);

    /**
     * 根据主键更新实体对象信息
     *
     * @param entity 二维码实体对象
     * @return 更新成功返回true，更新失败返回false
     */
    boolean update(IdentityQrcode entity);
}

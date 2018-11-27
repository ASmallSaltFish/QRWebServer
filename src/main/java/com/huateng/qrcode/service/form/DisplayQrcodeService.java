package com.huateng.qrcode.service.form;

import com.baomidou.mybatisplus.service.IService;
import com.huateng.qrcode.common.model.DisplayQrcode;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
public interface DisplayQrcodeService extends IService<DisplayQrcode> {
    /**
     * 根据token获取二维码记录
     *
     * @param token token值
     * @return 返回二维码实体对象
     */
    DisplayQrcode findByToken(String token);
}

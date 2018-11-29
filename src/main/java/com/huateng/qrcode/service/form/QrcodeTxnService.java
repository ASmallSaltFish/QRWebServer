package com.huateng.qrcode.service.form;

import com.baomidou.mybatisplus.service.IService;
import com.huateng.qrcode.common.model.QrcodeTxn;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
public interface QrcodeTxnService extends IService<QrcodeTxn> {

    /**
     * 根据主键更新二维码流水记录
     *
     * @param entity 二维码流水实体
     * @return 更新成功返回true，失败返回false
     */
    boolean update(QrcodeTxn entity);
}

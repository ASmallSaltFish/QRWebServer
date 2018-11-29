package com.huateng.qrcode.service.form;

import com.baomidou.mybatisplus.service.IService;
import com.huateng.qrcode.common.exception.QrcException;
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

    /**
     * 根据主键更新实体
     *
     * @param entity 二维码实体信息
     * @return true表示更新成功，false表示更新失败
     */
    boolean update(DisplayQrcode entity);

    /**
     * 添加展示类二维码信息
     *
     * @return
     */
    public boolean addDisplayQrcode(String ewmData, String templetId, String customModDate, String customModTime,
                                    String token, String beforeToken, String expiryDateTime, String expiryStatus,
                                    String pictureId, String crtUser, String data, String orgId, String qrUrl,
                                    String updateUser) throws QrcException;

}

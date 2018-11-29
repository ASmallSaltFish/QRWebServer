package com.huateng.qrcode.service.form.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.ExpiryStatusEnum;
import com.huateng.qrcode.common.exception.QrcException;
import com.huateng.qrcode.common.mapper.DisplayQrcodeMapper;
import com.huateng.qrcode.common.model.DisplayQrcode;
import com.huateng.qrcode.service.form.DisplayQrcodeService;
import com.huateng.qrcode.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeAnnos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@Service
public class DisplayQrcodeServiceImpl extends ServiceImpl<DisplayQrcodeMapper, DisplayQrcode> implements DisplayQrcodeService {

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public DisplayQrcode findByToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("param token is null or empty");
        }

        EntityWrapper<DisplayQrcode> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("token={0}", token);
        return selectOne(entityWrapper);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean update(DisplayQrcode entity) {
        String qrcodeId = entity.getQrcodeId();
        if (StringUtils.isBlank(qrcodeId)) {
            throw new RuntimeException("entity primary key qrcodeId is must be not null");
        }

        EntityWrapper<DisplayQrcode> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("qrcode_id={0}", qrcodeId);
        return update(entity, entityWrapper);
    }

    /**
     * 添加展示类二维码信息
     * @return
     */
    public boolean addDisplayQrcode(String ewmData,String templetId,String customModDate ,String customModTime,
                                    String token,String beforeToken,String expiryDateTime,String expiryStatus,
                                    String pictureId, String crtUser,String data,String orgId,String qrUrl,
                                    String updateUser) throws QrcException {

        //获取当前日期（yyyyMMdd格式）
        String currentDate = DateUtil.formatCurrentDate();
        //获取当前日期和时间（yyyyMMddHHmmss格式）
        String currentTime = DateUtil.formatCurrentDateTime().substring(8);

        DisplayQrcode displayQrcode = new DisplayQrcode();
        displayQrcode.setQrcodeId(ewmData);
        displayQrcode.setTempletId(templetId);
        displayQrcode.setCustomModDate(customModDate);
        displayQrcode.setCustomModTime(customModTime);
        displayQrcode.setToken(token);
        displayQrcode.setBeforeToken(beforeToken);
        displayQrcode.setExpiryDateTime(expiryDateTime);
        displayQrcode.setExpiryStatus(expiryStatus);
        displayQrcode.setPictureId(pictureId);
        displayQrcode.setCrtDate(currentDate);
        displayQrcode.setCrtTime(currentTime);
        displayQrcode.setCrtUser(crtUser);
        displayQrcode.setData(data);
        displayQrcode.setOrgId(orgId);
        displayQrcode.setQrUrl(qrUrl);
        displayQrcode.setUpdateUser(updateUser);
        displayQrcode.setUpdDate(currentDate);

        return insert(displayQrcode);
    }
}

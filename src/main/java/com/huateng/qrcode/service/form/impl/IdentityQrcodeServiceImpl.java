package com.huateng.qrcode.service.form.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.huateng.qrcode.common.mapper.IdentityQrcodeMapper;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import com.huateng.qrcode.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@Service
public class IdentityQrcodeServiceImpl extends ServiceImpl<IdentityQrcodeMapper, IdentityQrcode> implements IdentityQrcodeService {

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public IdentityQrcode findByToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("param token is null or empty");
        }

        EntityWrapper<IdentityQrcode> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("token = {0}", token);
        return selectOne(entityWrapper);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean update(IdentityQrcode entity) {
        String qrcodeId = entity.getQrcodeId();
        if (StringUtils.isBlank(qrcodeId)) {
            throw new RuntimeException("update entity primary key qrcodeId must be not null");
        }

        entity.setUpdDate(DateUtil.formatCurrentDate());
        EntityWrapper<IdentityQrcode> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("qrcodeId={0}", qrcodeId);
        return update(entity, entityWrapper);
    }
}

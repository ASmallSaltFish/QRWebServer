package com.huateng.qrcode.service.form.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.huateng.qrcode.common.mapper.IdentityQrcodeMapper;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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

    @Override
    public IdentityQrcode findByToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("param token is null or empty");
        }

        EntityWrapper<IdentityQrcode> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("token = {0}", token);
        return selectOne(entityWrapper);
    }
}

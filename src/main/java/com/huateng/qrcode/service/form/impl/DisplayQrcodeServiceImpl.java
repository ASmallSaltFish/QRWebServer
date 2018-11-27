package com.huateng.qrcode.service.form.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.huateng.qrcode.common.mapper.DisplayQrcodeMapper;
import com.huateng.qrcode.common.model.DisplayQrcode;
import com.huateng.qrcode.service.form.DisplayQrcodeService;
import org.apache.commons.lang3.StringUtils;
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
}

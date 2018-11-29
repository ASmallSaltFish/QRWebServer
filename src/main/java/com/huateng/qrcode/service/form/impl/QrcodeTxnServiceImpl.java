package com.huateng.qrcode.service.form.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.huateng.qrcode.common.mapper.QrcodeTxnMapper;
import com.huateng.qrcode.common.model.QrcodeTxn;
import com.huateng.qrcode.service.form.QrcodeTxnService;
import com.huateng.qrcode.utils.DateUtil;
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
public class QrcodeTxnServiceImpl extends ServiceImpl<QrcodeTxnMapper, QrcodeTxn> implements QrcodeTxnService {


    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean update(QrcodeTxn entity) {
        String txnId = entity.getTxnId();
        if (StringUtils.isBlank(txnId)) {
            throw new RuntimeException("update entity primary key txnId must be not null");
        }

        entity.setUpdDate(DateUtil.formatCurrentDate());
        EntityWrapper<QrcodeTxn> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("txn_id={0}", txnId);
        return update(entity, entityWrapper);
    }
}

package com.huateng.qrcode.service.form.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.common.exception.QrcException;
import com.huateng.qrcode.common.mapper.QrcodeTxnMapper;
import com.huateng.qrcode.common.model.BlackListInfo;
import com.huateng.qrcode.common.model.QrcodeTxn;
import com.huateng.qrcode.qrserver.impl.DisplayQrGenerateImpl;
import com.huateng.qrcode.service.form.QrcodeTxnService;
import com.huateng.qrcode.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import com.huateng.qrcode.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 添加交易流水
     * @return
     * @throws QrcException
     */
    @Override
    public Boolean addTxnService(String orgTxnId, String qrcodeId, String channelId, String channelNo,
                                 String channelDate, String channelTime, String reqSysNo, String respSysNo,
                                 String application, String purpose, String scene, String requestData,
                                 String transType, String receiveDate, String receiveTime, String status,
                                 String remark, String returnCode,String returnDesc, String orgId,
                                 String updateUser,String crtUser) throws QrcException {

        //获取当前日期（yyyyMMdd格式）
        String currentDate = DateUtil.formatCurrentDate();
        //获取当前日期和时间（yyyyMMddHHmmss格式）
        String currentTime = DateUtil.formatCurrentDateTime().substring(8);

        QrcodeTxn qrcodeTxn = new QrcodeTxn();
        qrcodeTxn.setTxnId(IdWorker.getIdStr());
        qrcodeTxn.setOrgTxnId(orgTxnId);
        qrcodeTxn.setQrcodeId(qrcodeId);
        qrcodeTxn.setChannelId(channelId);
        qrcodeTxn.setChannelNo(channelNo);
        qrcodeTxn.setChannelDate(channelDate);
        qrcodeTxn.setChannelTime(channelTime);
        qrcodeTxn.setReqSysNo(reqSysNo);
        qrcodeTxn.setRespSysNo(respSysNo);
        qrcodeTxn.setApplication(application);
        qrcodeTxn.setPurpose(purpose);
        qrcodeTxn.setScene(scene);
        qrcodeTxn.setRequestData(requestData);
        qrcodeTxn.setTransType(transType);
        qrcodeTxn.setReceiveDate(receiveDate);
        qrcodeTxn.setReceiveTime(receiveTime);
        qrcodeTxn.setStatus(status);
        qrcodeTxn.setRemark(remark);
        qrcodeTxn.setReturnCode(returnCode);
        qrcodeTxn.setReturnDate(currentDate);
        qrcodeTxn.setReturnDesc(returnDesc);
        qrcodeTxn.setReturnTime(currentTime);
        qrcodeTxn.setOrgId(orgId);
        qrcodeTxn.setUpdateUser(updateUser);
        qrcodeTxn.setUpdDate(currentDate);
        qrcodeTxn.setCrtDate(currentDate);
        qrcodeTxn.setCrtTime(currentTime);
        qrcodeTxn.setCrtUser(crtUser);

        return insert(qrcodeTxn);
    }
}

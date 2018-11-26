package com.huateng.qrcode.service.seq.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huateng.qrcode.common.model.SeqInfo;
import com.huateng.qrcode.service.form.SeqInfoService;
import com.huateng.qrcode.service.seq.SeqGeneratorService;
import com.huateng.qrcode.utils.StringUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;

@Service(value = "seqGeneratorService")
public class SeqGeneratorServiceImpl implements SeqGeneratorService {

    private static Logger logger = LoggerFactory.getLogger(SeqGeneratorServiceImpl.class);

    @Autowired
    private SeqInfoService seqInfoService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public LinkedList<String> generateSeqNos(String seqKey) {
        LinkedList<String> linkedList = new LinkedList<>();
        EntityWrapper<SeqInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("seq_key={0}", seqKey);
        SeqInfo seqInfo = seqInfoService.selectOne(entityWrapper);
        if (seqInfo == null) {
            logger.error("根据联合主键seqKey和seqId数据库中没有找到序列配置记录!");
            throw new RuntimeException("根据联合主键seqKey和seqId数据库中没有找到序列配置记录！");
        }

        //一次性读取到缓存中的序列号数
        BigDecimal incRuleValue = seqInfo.getIncRuleValue();
        if (incRuleValue == null) {
            incRuleValue = new BigDecimal(10);
        }

        //初始值
        BigDecimal initValue = seqInfo.getInitValue();
        //下限
        BigDecimal downLimit = seqInfo.getDownLimit();
        //上限
        BigDecimal upLimit = seqInfo.getUpLimit();
        // todo 这个可以为生成的位数
        String ruleDef = seqInfo.getRuleDef();

        //初始值如果大于上限，则重置初始值
        if (initValue.compareTo(upLimit) >= 0) {
            initValue = new BigDecimal(0);
        }

        //限制初始值不能小于设置下限值
        if (initValue.compareTo(downLimit) < 0) {
            incRuleValue = downLimit;
        }

        int interval = incRuleValue.intValue();
        if (initValue.add(incRuleValue).compareTo(upLimit) > 0) {
            interval = upLimit.subtract(initValue).intValue();
        }

        //先更新，在将更新前的值存储在缓存里
        SeqInfo updateSeqInfo = new SeqInfo();
        updateSeqInfo.setInitValue(initValue.add(incRuleValue));
        boolean isUpdated = seqInfoService.update(updateSeqInfo, entityWrapper);
        if (BooleanUtils.isFalse(isUpdated)) {
            logger.error("序列服务表更新操作失败，请检查数据库连接!");
            throw new RuntimeException("序列服务表更新操作失败，请检查数据库连接！");
        }

        for (int i = 0; i < interval; i++) {
            initValue = initValue.add(new BigDecimal(1));
            String seqNo = StringUtil.transToSeqNo(initValue, Integer.parseInt(ruleDef));
            linkedList.add(seqNo);
        }

        return linkedList;
    }
}

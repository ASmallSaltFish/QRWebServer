package com.huateng.qrcode.utils;

import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.service.seq.SeqGeneratorService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeqGeneratorUtil {

    private Map<String, LinkedList<String>> seqKeyAndSeqNosMap = new ConcurrentHashMap<>();

    private SeqGeneratorUtil() {

    }

    public static SeqGeneratorUtil getInstance() {
        return StaticHelper.instance;
    }

    private static class StaticHelper {
        private static final SeqGeneratorUtil instance = new SeqGeneratorUtil();
    }

    public synchronized String getSequenceNo(String seqKey) {
        LinkedList<String> sequenceNos = seqKeyAndSeqNosMap.get(seqKey);
        if (CollectionUtils.isEmpty(sequenceNos)) {
            ApplicationContext ctx = SpringContextUtil.getInstance().getApplicationContext();
            SeqGeneratorService seqGeneratorService = (SeqGeneratorService) ctx.getBean("seqGeneratorService");
            if (seqGeneratorService == null) {
                throw new RuntimeException("序列生成服务类实例加载失败！");
            }

            sequenceNos = seqGeneratorService.generateSeqNos(seqKey);
            seqKeyAndSeqNosMap.put(seqKey, sequenceNos);
        }

        return sequenceNos.remove();
    }
}

package com.huateng.qrcode.utils;

import com.huateng.qrcode.service.seq.SeqGeneratorService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SeqGeneratorUtil {

    @Autowired
    private SeqGeneratorService seqGeneratorService;

    private static SeqGeneratorUtil instance;

    private Map<String, LinkedList<String>> seqKeyAndSeqNosMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        instance = this;
    }

    private SeqGeneratorUtil() {

    }

    public static SeqGeneratorUtil getInstance() {
        return SeqGeneratorUtil.instance;
    }


    public synchronized String getSequenceNo(String seqKey) {
        LinkedList<String> sequenceNos = seqKeyAndSeqNosMap.get(seqKey);
        if (CollectionUtils.isEmpty(sequenceNos)) {
            if (seqGeneratorService == null) {
                throw new RuntimeException("序列生成服务类实例加载失败！");
            }

            sequenceNos = seqGeneratorService.generateSeqNos(seqKey);
            seqKeyAndSeqNosMap.put(seqKey, sequenceNos);
        }

        return sequenceNos.remove();
    }
}

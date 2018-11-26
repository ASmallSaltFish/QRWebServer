package com.huateng.qrcode.service.seq;

import java.util.LinkedList;

public interface SeqGeneratorService {

    /**
     * 根据指定序列服务key，批量生成序列号集合
     *
     * @param seqKey 序列服务key
     * @return 序列号集合
     */
    LinkedList<String> generateSeqNos(String seqKey);
}

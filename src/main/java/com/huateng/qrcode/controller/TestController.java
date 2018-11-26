package com.huateng.qrcode.controller;

import com.huateng.qrcode.utils.SeqGeneratorUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/")
public class TestController {


    @RequestMapping(value = "/test")
    public String test() {

        return SeqGeneratorUtil.getInstance().getSequenceNo();
    }


    /**
     * 多线程测试序列生成
     */
    @RequestMapping(value = "/testSeq")
    public String testSeq() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("==========>>>>>>");
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                String sequenceNo = SeqGeneratorUtil.getInstance().getSequenceNo();
                System.out.println("==========>>>>>>" + sequenceNo);
            });
        }

        executorService.shutdown();
        return "测试序列生成";
    }
}

package com.huateng.test.mybatis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huateng.qrcode.common.mapper.OperationQrcodeMapper;
import com.huateng.qrcode.common.model.OperationQrcode;
import com.huateng.qrcode.service.OperationQrcodeService;
import com.huateng.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 单元测试mybatis-plus集成后提供的数据接口
 *
 * @author qinyupeng
 * @since 2018-11-14 09:05:38
 */
public class TestMp extends BaseTest {

    @Autowired
    private OperationQrcodeService operationQrcodeService;

    @Autowired
    private OperationQrcodeMapper operationQrcodeMapper;


    @Test
    public void test() {
        System.out.println(operationQrcodeMapper);
        System.out.println(operationQrcodeService);
        List<OperationQrcode> list = operationQrcodeService.selectList(new EntityWrapper<>());
    }
}

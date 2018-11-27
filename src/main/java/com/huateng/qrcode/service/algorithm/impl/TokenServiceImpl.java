package com.huateng.qrcode.service.algorithm.impl;

import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.exception.QrcException;
import com.huateng.qrcode.qrserver.impl.DisplayQrGenerateImpl;
import com.huateng.qrcode.service.algorithm.MovePositionService;
import com.huateng.qrcode.service.algorithm.TokenService;
import com.huateng.qrcode.utils.SeqGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @class: TokenServiceImpl
 * @title: Token实现类
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
@Service(value = "TokenServiceImpl")
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(DisplayQrGenerateImpl.class);

    @Autowired
    private MovePositionService movePositionService;

    /**
     * 获取token
     *
     * @return
     * @throws QrcException
     */
    @Override
    public String getToken() throws QrcException {
        logger.info("-----获取序列开始-----");
        // 获取序列
        String svSeq = SeqGeneratorUtil.getInstance().getSequenceNo(Constants.TOKEN_ID);
        // 生成随机数
        String fvRandom = getFixLenthString(5);
        // 移位
        String token = movePositionService.movePosition(svSeq + fvRandom, 3);
        token = token.replaceAll(" ", "");
        System.out.println("token.length:" + token.length());
        logger.info("-----获取序列结束，出参:" + token);
        ;
        return token;
    }

    /**
     * 生成随机数
     */
    private static String getFixLenthString(int strLength) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < strLength; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}

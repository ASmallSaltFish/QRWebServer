package com.huateng.qrcode.service.form.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.huateng.qrcode.common.enums.BlackInfoUseEnum;
import com.huateng.qrcode.common.mapper.BlackListInfoMapper;
import com.huateng.qrcode.common.model.BlackListInfo;
import com.huateng.qrcode.service.form.BlackListInfoService;
import com.huateng.qrcode.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@Service
public class BlackListServiceImpl extends ServiceImpl<BlackListInfoMapper, BlackListInfo> implements BlackListInfoService {

    private static Logger logger = LoggerFactory.getLogger(BlackListServiceImpl.class);

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean existInBlackListInfo(String blackNo, String blackType) {
        if (StringUtils.isBlank(blackNo) || StringUtils.isBlank(blackType)) {
            throw new RuntimeException("param blackNo or blackType is null or empty");
        }

        EntityWrapper<BlackListInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("black_no={0} and black_type={1}", blackNo, blackType);
        return selectCount(entityWrapper) > 0;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean validInBlackListInfo(String blackNo, String blackType) {
        if (StringUtils.isBlank(blackNo) || StringUtils.isBlank(blackType)) {
            throw new RuntimeException("param blackNo or blackType is null or empty");
        }

        EntityWrapper<BlackListInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("black_no={0} and black_type={1}", blackNo, blackType);
        BlackListInfo blackInfo = selectOne(entityWrapper);
        if (blackInfo == null) {
            logger.debug("黑名单信息不存在！");
            return false;
        }

        String isUse = blackInfo.getIsUse();
        if (BlackInfoUseEnum.INVALID.getCode().equals(isUse)) {
            logger.debug("黑名单设置已停用！");
            return false;
        }

        String expiryTimeStr = blackInfo.getExpiryTime();
        if (StringUtils.isBlank(expiryTimeStr)) {
            logger.debug("黑名单设置未设置失效时间！");
            return false;
        }

        try {
            Date expiryTime = DateUtil.parserDate(expiryTimeStr);
            if (expiryTime.before(new Date())) {
                logger.debug("黑名单在有效期内！");
                return true;
            }
        } catch (ParseException e) {
            logger.error("黑名单失效时间解析异常！", e);
            throw new RuntimeException("黑名单失效时间解析错误！");
        }

        return false;
    }

}

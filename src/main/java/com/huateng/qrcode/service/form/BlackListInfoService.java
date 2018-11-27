package com.huateng.qrcode.service.form;

import com.baomidou.mybatisplus.service.IService;
import com.huateng.qrcode.common.model.BlackListInfo;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
public interface BlackListInfoService extends IService<BlackListInfo> {

    /**
     * 判断是否存在黑名单记录中
     *
     * @param blackNo   黑名单关联编号
     * @param blackType 黑名单类型（SYS,MODULE,QRCODE）
     * @return true表示加入黑名单，false表示没有加入黑名单
     */
    boolean existInBlackListInfo(String blackNo, String blackType);


    /**
     * 判断设置黑名单是否生效
     *
     * @param blackNo   黑名单关联编号
     * @param blackType 黑名单类型（SYS,MODULE,QRCODE）
     * @return true表示加入黑名单，false表示没有加入黑名单
     */
    boolean validInBlackListInfo(String blackNo, String blackType);
}

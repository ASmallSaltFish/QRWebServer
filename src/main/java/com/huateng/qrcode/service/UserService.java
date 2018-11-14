package com.huateng.qrcode.service;

import com.huateng.qrcode.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-14
 */
public interface UserService extends IService<User> {

    User findUserByUserId(String userId);
}

package com.huateng.qrcode.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.huateng.qrcode.common.mapper.UserMapper;
import com.huateng.qrcode.common.model.User;
import com.huateng.qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserId(String userId) {
        int i = 1 / 0;
        return userMapper.findUserByUserId(userId);
    }
}

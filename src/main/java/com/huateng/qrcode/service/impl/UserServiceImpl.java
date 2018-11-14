package com.huateng.qrcode.service.impl;

import com.huateng.qrcode.entity.User;
import com.huateng.qrcode.mapper.UserMapper;
import com.huateng.qrcode.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
        return userMapper.findUserByUserId(userId);
    }
}

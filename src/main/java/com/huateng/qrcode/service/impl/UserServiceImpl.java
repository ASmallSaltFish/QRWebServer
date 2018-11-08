package com.huateng.qrcode.service.impl;

import com.huateng.qrcode.entity.User;
import com.huateng.qrcode.service.UserService;
import com.huateng.qrcode.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserId(String userId) {
        return userMapper.findUserByUserId(userId);
    }
}

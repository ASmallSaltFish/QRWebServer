package com.huateng.qrcode.service.mapper;


import com.huateng.qrcode.entity.User;
import com.huateng.qrcode.entity.UserExample;

public interface UserMapper {

    User selectByExample(UserExample userExample);

    User findUserByUserId(String userId);
}

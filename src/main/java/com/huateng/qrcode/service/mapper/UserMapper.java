package com.huateng.qrcode.service.mapper;


import com.huateng.qrcode.entity.User;

public interface UserMapper {

    User findUserByUserId(String userId);
}

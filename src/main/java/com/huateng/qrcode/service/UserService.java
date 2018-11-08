package com.huateng.qrcode.service;


import com.huateng.qrcode.entity.User;

public interface UserService {

    User findUserByUserId(String userId);
}

package com.huateng.qrcode.dao;

import com.huateng.qrcode.entity.User;

public interface UserDao {

    User getUserByUserName(String userName);
}

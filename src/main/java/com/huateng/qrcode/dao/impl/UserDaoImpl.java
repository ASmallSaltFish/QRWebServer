package com.huateng.qrcode.dao.impl;

import com.huateng.qrcode.dao.UserDao;
import com.huateng.qrcode.entity.User;
import com.huateng.qrcode.entity.UserExample;
import com.huateng.qrcode.service.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public User findByUser(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        example.setOrderByClause("user_name desc");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userFromDB = mapper.selectByExample(example);
        System.out.println(userFromDB);
        return userFromDB;
    }
}

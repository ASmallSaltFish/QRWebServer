package com.huateng.test.mybatis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huateng.qrcode.common.model.User;
import com.huateng.qrcode.common.mapper.UserMapper;
import com.huateng.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestPagerHelper extends BaseTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPagerHelper() {
        PageHelper.startPage(1, 10);
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.like("user_name", "ceshi");
        List<User> userList = userMapper.findUserPager("%ceshi%");
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("====>>total=" + pageInfo.getTotal());
        System.out.println("====>>pages=" + pageInfo.getPages());
        //默认导航总页数为8页
        System.out.println("====>>lastPage=" + pageInfo.getLastPage());
        System.out.println("====>>>isLastPage=" + pageInfo.isIsLastPage());
    }
}

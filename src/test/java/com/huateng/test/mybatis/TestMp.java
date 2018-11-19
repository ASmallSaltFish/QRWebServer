package com.huateng.test.mybatis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huateng.qrcode.common.model.User;
import com.huateng.qrcode.service.UserService;
import com.huateng.test.BaseTest;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 单元测试mybatis-plus集成后提供的数据接口
 *
 * @author qinyupeng
 * @since 2018-11-14 09:05:38
 */
public class TestMp extends BaseTest {

    @Resource
    private UserService userService;


    /**
     * 测试插入单条记录
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUserId("1111");
        user.setUserName("张三");
        user.setType("0");
        user.setCreateTime(new Date());
        boolean inserted = userService.insert(user);
        System.out.println(BooleanUtils.isTrue(inserted) ? "插入数据成功！" : "插入数据失败！");
    }

    /**
     * 测试批量插入数据（测试插入1000条数据1455ms）
     */
    @Test
    public void testBatchInsert() {
        List<User> userList = new ArrayList<>(100);
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setUserId(4444 + (i + 1) + "");
            user.setType("0");
            user.setUserName("ceshi" + i);
            user.setCreateTime(new Date());
            userList.add(user);
        }

        long startTime = System.currentTimeMillis();
        boolean b = userService.insertBatch(userList);
        System.out.println(BooleanUtils.isTrue(b) ? "批量插入数据成功！" : "批量插入数据失败！");
        System.out.println("====>>>共耗时：" + (System.currentTimeMillis() - startTime));
    }


    /**
     * 测试批量删除
     */
    @Test
    public void testDelete() {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("user_id", Arrays.asList("3333", "4444"));
        boolean deleted = userService.delete(entityWrapper);
        System.out.println(BooleanUtils.isTrue(deleted) ? "删除数据成功！" : "删除数据失败！");
    }


    /**
     * 测试查询
     */
    @Test
    public void testSelect() {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("user_id={0}", "1111");
        List<User> userList = userService.selectList(entityWrapper);
        System.out.println("===>>>" + userList.get(0));
    }


    /**
     * 测试mybatis-plus自带的分页
     */
    @Test
    public void testPager() {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.like("user_name", "ceshi");
        Page<User> userPage = userService.selectPage(new Page<User>(1, 20), entityWrapper);
        if (userPage != null) {
            List<User> userList = userPage.getRecords();
            System.out.println("===>>>>" + userList.size());
            System.out.println(userList);
        }
    }
}

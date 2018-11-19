package com.huateng.qrcode.common.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.huateng.qrcode.common.model.User;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author auto generator
 * @since 2018-11-14
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义接口，对应xml中sql
     */
    User findUserByUserId(String userId);

    /**
     * 测试pagerHelper分页查询
     */
    List<User> findUserPager(String userName);
}

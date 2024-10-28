package com.stray.cat.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.UserMapper;
import com.stray.cat.dto.CatConst;
import com.stray.cat.pojo.Sponsor;
import com.stray.cat.pojo.User;
import com.stray.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {
    private static final String USER_CACHE_NAME = "USER";

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryByNameAndPassword(String userName, String userPassword) {
        return userMapper.queryByNameAndPassword(userName,userPassword);
    }

    @Override
    public User queryByPhone(String userPhone) {
        return userMapper.queryByPhone(userPhone);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUserPassword(String newPassword, int userId) {
        return userMapper.updateUserPassword(newPassword,userId);
    }

    @Override
    public int updateUserPhone(String newPassword, String userPhone) {
        return userMapper.updateUserPhone(newPassword,userPhone);
    }

    @Override
    public int deleteUser(int userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    @Cacheable(value = USER_CACHE_NAME, key = "'USER'+#page+#limit")
    public PageInfo<User> queryUserAll(int page, int limit) {
        PageHelper.startPage(page,limit);
        List<User> users=userMapper.queryUserAll();
        return new PageInfo<User>(users);
    }

    @Override
    public User queryUserById(int userId) {
        return userMapper.queryUserById(userId);
    }
}

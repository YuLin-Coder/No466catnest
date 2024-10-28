package com.stray.cat.service;

import com.github.pagehelper.PageInfo;
import com.stray.cat.pojo.Sponsor;
import com.stray.cat.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserService {

    User queryByNameAndPassword(String userName, String userPassword);

    User queryByPhone(String userPhone);

    int addUser(User user);

    int updateUserPassword(String newPassword,int userId);

    int updateUserPhone(String newPassword,String userPhone);

    int deleteUser(int userId);

    int updateUser(User user);

    PageInfo<User> queryUserAll(int page, int limit);

    User queryUserById(int userId);
}

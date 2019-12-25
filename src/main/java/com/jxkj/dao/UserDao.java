package com.jxkj.dao;


import com.jxkj.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    //查看所有用户
    List<User> userList();

    //登录
    User userLogin(String name);

    //通过id来找到一个用户
    User findUserById(String id);

    //通过id获取用户并修改密码
    void changPwd(@Param("id") String id, @Param("password") String password);

    //增加新的用户
    void addUser(User user);

    //改变用户的状态
    void changStatus(@Param("id") String id, @Param("status") Integer status);
}

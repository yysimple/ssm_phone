package com.jxkj.service;


import com.jxkj.model.Result;

public interface UserService {
    /**
     * 查找所有的用户
     *
     * @return
     */
    Result userList();

    /**
     * 登录验证
     *
     * @param name
     * @param userPwd
     * @return
     */
    Result login(String name, String userPwd);

    /**
     * 通过输入Id来找到该用户
     * @param id
     * @return
     */
    Result findUserById(String id);

    /**
     * 修改密码，通过Id找到该用户，然后对其进行修改
     * @param user_id
     * @param mpass
     * @param newpass
     * @return
     */
    Result changePwd(String user_id, String mpass, String newpass);

    /**
     * 新增一个用户
     * @param name
     * @param password
     * @param type
     * @return
     */
    Result addUser(String name, String password, Integer type);

    /**
     * 修改状态，通过Id找到该用户，然后对其进行修改
     * @param id
     * @return
     */
    Result changeStatus(String id);
}

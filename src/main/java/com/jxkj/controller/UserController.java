package com.jxkj.controller;

import com.jxkj.model.Result;
import com.jxkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/show.do")
    public Result userList(){
        return userService.userList();
    }

    @RequestMapping("/login.do")
    public Result login(String name,String password){
        System.out.println(name + "--" + password);
        return userService.login(name,password);
    }

    @RequestMapping("/find.do")
    public Result findUserById(String id){
        return userService.findUserById(id);
    }

    @RequestMapping("/changepwd.do")
    public Result changePwd(String user_id, String mpass, String newpass){
        return userService.changePwd(user_id,mpass,newpass);
    }

    @RequestMapping("/add.do")
    public Result addUser(String name, String password, Integer type){
        return userService.addUser(name,password,type);
    }

    @RequestMapping("/change.do")
    public Result changeStatus(String id){
        return userService.changeStatus(id);
    }



}

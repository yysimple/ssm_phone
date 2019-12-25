package com.jxkj.service.impl;
import com.jxkj.dao.PersonalInfoDao;
import com.jxkj.dao.UserDao;
import com.jxkj.dao.VisitorDao;
import com.jxkj.model.Result;
import com.jxkj.model.User;
import com.jxkj.model.Visitor;
import com.jxkj.service.UserService;
import com.jxkj.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private VisitorDao visitorDao;

    @Autowired
    private PersonalInfoDao personalInfoDao;

    @Override
    public Result userList() {
        //查询所有用户
        List<User> list = userDao.userList();
        return ResultsUtil.success(list);
    }

    @Override
    public Result login(String name, String userPwd) {
        //验证用户登录
        User user = userDao.userLogin(name);
        //密码加密
        String mdsPassword = MD5Util.md5(userPwd);
        //判断用户名
        if(user == null){
            return ResultsUtil.error(1,"用户名不存在");
        //判断密码
        }else if(!user.getPassword().equals(mdsPassword)){
            return ResultsUtil.error(1,"你输入的密码有误,请从新输入");
        //判断用户状态
        }else if(user.getStatus() == 1){
            return ResultsUtil.error(1,"该用户已被禁用");
        }else {
            Integer type = user.getType();
            String userId = user.getId();
            //封装数据
            Map<String,Object> data = new HashMap<>();
            data.put("type",type);
            data.put("userId",userId);
            //判断是否为管理员
                if (user.getType() == 0){
                    List<Visitor>  visitors = visitorDao.visitorList();
                    //判断是否访问记录大于12
                    if(visitors.size() >= 12){
                        //大于12自动删除
                        visitorDao.delVisitor();
                    }
                    //小于12条记录时，增加新的记录
                    Visitor visitor = new Visitor();
                    visitor.setId(IDUtil.createId());
                    visitor.setV_name(name);
                    //获取到访问的时间
                    visitor.setV_time(DateUtil.getTime());
                    //获取毫秒数
                    visitor.setLong_time(System.currentTimeMillis());
                    visitorDao.addVisitor(visitor);
                }
               return ResultsUtil.success(data);
        }

    }

    @Override
    public Result findUserById(String id){
        //通过id找到用户
        User user = userDao.findUserById(id);
        return ResultsUtil.success(user);
    }

    @Override
    public Result changePwd(String user_id, String mpass, String newpass) {
        User user = userDao.findUserById(user_id);
        //判断原密码是否为空
        if(StringUtil.isEmpty(mpass)){
            return ResultsUtil.error(1,"请输入原密码");
        //判断新密码是否为空
        }else if(StringUtil.isEmpty(newpass)){
            return ResultsUtil.error(1,"请输入新的密码");
        //判断两者是否都为空
        }else if(StringUtil.isEmpty(mpass) && StringUtil.isEmpty(newpass)){
            return ResultsUtil.error(1,"请输密码");
        //判断原密码是否正确
        }else if (!MD5Util.md5(mpass).equals(user.getPassword())){
            return ResultsUtil.error(1,"原密码不正确");
        }else {
            //修改密码
            userDao.changPwd(user_id,MD5Util.md5(newpass));
            return ResultsUtil.success();
        }

    }

    @Override
    public Result addUser(String name, String password, Integer type) {
        //查找所有用户用来判断是否存在该用户
        List<User> users = userDao.userList();
        if(StringUtil.isEmpty(name)){
            return ResultsUtil.error(1,"用户名不能为空");
        }else if(StringUtil.isEmpty(password)){
            return ResultsUtil.error(1,"密码不能为空");
        }else {
            //判断是否存在用户名
            for(int i = 0; i < users.size(); i++){
                if (users.get(i).getName().equals(name)){
                    return ResultsUtil.error(1,"用户名已经存在");
                }
            }
            //保存用户
            User user = new User();
            user.setId(IDUtil.createId());
            user.setName(name);
            user.setPassword(MD5Util.md5(password));
            user.setType(type);
            user.setStatus(0);
            userDao.addUser(user);
            //将用户注册时指定的id存入到用户的个人信息中
            personalInfoDao.addUserId(user.getId());
            return ResultsUtil.success();
        }
    }

    @Override
    public Result changeStatus(String id) {
        //获取到该用户
        User user = userDao.findUserById(id);
        //获取其状态
        Integer status = user.getStatus();
        //状态为0，则改成1；
        if(status == 0){
            userDao.changStatus(id,1);
        }else {
            userDao.changStatus(id,0);
        }
        return ResultsUtil.success();
    }
}

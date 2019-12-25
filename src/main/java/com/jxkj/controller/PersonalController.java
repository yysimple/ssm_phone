package com.jxkj.controller;

import com.jxkj.model.PersonalInfo;
import com.jxkj.model.Result;
import com.jxkj.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personalinfo")
public class PersonalController {

    @Autowired
    private PersonalInfoService personalInfoService;

    @RequestMapping("/update.do")
    public Result updatePersonalInfo(String user_id, String real_name, String sex, String birthday
            , String email, String address, String tel_num, String remark){
        return personalInfoService.updatePersonalInfo(user_id, real_name,  sex,  birthday
                ,  email,address, tel_num, remark);
    }

    @RequestMapping("/show.do")
    public Result showPersonalInfo(String user_id){
        return personalInfoService.showPersonalInfo(user_id);
    }
}

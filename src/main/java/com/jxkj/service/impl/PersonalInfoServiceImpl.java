package com.jxkj.service.impl;

import com.jxkj.dao.PersonalInfoDao;
import com.jxkj.model.PersonalInfo;
import com.jxkj.model.Result;
import com.jxkj.service.PersonalInfoService;
import com.jxkj.utils.ResultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private PersonalInfoDao personalInfoDao;
    @Override
    public Result updatePersonalInfo(String user_id, String real_name, String sex,
                                     String birthday, String email, String address, String tel_num, String remark) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setUser_id(user_id);
        personalInfo.setReal_name(real_name);
        personalInfo.setSex(sex);
        personalInfo.setBirthday(birthday);
        personalInfo.setEmail(email);
        personalInfo.setAddress(address);
        personalInfo.setTel_num(tel_num);
        personalInfo.setRemark(remark);
        personalInfoDao.updatePersonalInfo(personalInfo);
        return ResultsUtil.success();

    }

    @Override
    public Result showPersonalInfo(String user_id) {
        PersonalInfo personalInfo = personalInfoDao.showPersonalInfo(user_id);
        return ResultsUtil.success(personalInfo);
    }
}

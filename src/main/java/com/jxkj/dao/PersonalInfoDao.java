package com.jxkj.dao;


import com.jxkj.model.PersonalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoDao {
    //更新个人信息
    void updatePersonalInfo(PersonalInfo personalInfo);

    //查询所有信息
    PersonalInfo showPersonalInfo(@Param("user_id") String user_id);

    //根据Id插入一个用户所有私人信息
    void addUserId(String user_id);
}

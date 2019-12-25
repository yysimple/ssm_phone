package com.jxkj.service;


import com.jxkj.model.Result;

public interface PersonalInfoService {
    /**
     * 修改个人的信息
     * @param user_id
     * @param real_name
     * @param sex
     * @param birthday
     * @param email
     * @param address
     * @param tel_num
     * @param remark
     * @return
     */
    Result updatePersonalInfo(String user_id, String real_name, String sex, String birthday
            , String email, String address, String tel_num, String remark);

    /**
     * 展示个人的信息
     * @param user_id
     * @return
     */
    Result showPersonalInfo(String user_id);
}

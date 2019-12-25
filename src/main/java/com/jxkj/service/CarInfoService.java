package com.jxkj.service;


import com.jxkj.model.Result;

public interface CarInfoService {
    /**
     * 添加一辆车
     * @param user_id
     * @param plate
     * @param brand
     * @param model
     * @param color
     * @param price
     * @param date
     * @param remark
     * @return
     */
    Result addCarInfo(
            String user_id,
            String plate,
            String brand,
            String model,
            String color,
            String price,
            String date,
            String remark);

    /**
     * 通过用户的Id，返回其所有车的信息
     * @param user_id
     * @return
     */
    Result showCarInfo(String user_id);

    /**
     * 通过用户id展示一辆车的信息
     * @param id
     * @return
     */
    Result showOneCar(String id);

    /**
     * 展示所有车的信息
     * @return
     */
    Result showAllCar();

    /**
     * 进行模糊查询
     * @param type
     * @param keywords
     * @return
     */
    Result searchCar(String type, String keywords);

    /**
     * 通过车牌号删除一辆车
     * @param id
     * @return
     */
    Result delCarInfo(String id);

    /**
     * 修改车的信息
     * @param id
     * @param plate
     * @param brand
     * @param model
     * @param color
     * @param price
     * @param date
     * @param remark
     * @return
     */
    Result updateCarInfo(
            String id,
            String plate,
            String brand,
            String model,
            String color,
            String price,
            String date,
            String remark);
}

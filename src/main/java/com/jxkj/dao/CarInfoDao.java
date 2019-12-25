package com.jxkj.dao;


import com.jxkj.model.CarInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
 public interface CarInfoDao {
	//新增一辆车
	 void addCarInfo(CarInfo carInfo);
	//通过用户的id，展示其所有车的信息
	 List<CarInfo> showCarInfo(@Param("user_id") String user_id);
	 //展示所有的车
	 List<CarInfo> showAllCar();
	 //通过id获得车的信息
	 CarInfo showOneCar(@Param("id") String id);
	 //根据其车的id删除一辆车
	 void delCarInfo(@Param("id") String id);
	 //跟新一辆车的信息
	 void updateCarInfo(CarInfo carInfo);
	//根据特定的条件展示车辆
	 List<CarInfo> searchCarInfo(Map<String, Object> params);
	
}

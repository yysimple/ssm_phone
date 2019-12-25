package com.jxkj.dao;


import com.jxkj.model.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderInfoDao {
	//添加维修订单信息
	 void addOrderInfo(OrderInfo orderInfo);

	//查找所有的订单
	 List<OrderInfo> findAllOrder();

	 //通过其ID删除订单
	 void delOrderInfo(String id);

	 //改变其状态
	 void changStatus(@Param("id") String id, @Param("status") Integer status);

	 //通过id返回所有信息，争对于个人
	 List<OrderInfo> findOrder(String user_id);

	 //进行模糊查找
	 List<OrderInfo> searchOrderInfo(Map<String, Object> params);

	 //通过车的状态找车
	 List<OrderInfo> findByStatus(Integer status);

	 //根据时间进行排序
	 List<OrderInfo> findBySort();
}

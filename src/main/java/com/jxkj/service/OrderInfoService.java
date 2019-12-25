package com.jxkj.service;


import com.jxkj.model.Result;

public interface OrderInfoService {
	/**
	 * 添加新的订单
	 * @param user_id
	 * @param user_name
	 * @param plate
	 * @param trouble_code
	 * @param trouble_name
	 * @param contact
	 * @param contact_way
	 * @param remark
	 * @return
	 */
	 Result addOrderInfo(String user_id, String user_name,
							   String plate, String trouble_code, String trouble_name,
							   String contact, String contact_way,
							   String remark);

	/**
	 * 查看所有的订单
	 * @return
	 */
	 Result findAllOrder();

	/**
	 * 通过id删除订单
	 * @param id
	 * @return
	 */
	 Result delOrderInfo(String id);

	/**
	 * 改变其状态，判断是否已维修
	 * @param id
	 * @return
	 */
	 Result changStatus(String id);

	/**
	 * 用户查找自己的订单
	 * @param user_id
	 * @return
	 */
	 Result findOrder(String user_id);

	/**
	 * 进行模糊查询
	 * @param type
	 * @param keywords
	 * @return
	 */
	 Result searchOrderInfo(String type, String keywords);

	/**
	 * 查询状态为1的订单
	 * @return
	 */
	 Result findDealOrder();

	/**
	 * 查询状态为0的账单
	 * @return
	 */
	 Result findUndealOrder();

	/**
	 * 根据时间顺序查询
	 * @return
	 */
	 Result findBySort();
}

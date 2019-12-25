package com.jxkj.service;


import com.jxkj.model.Result;

public interface TroubleInfoService {

	/**
	 * 添加新的异常信息
	 * @param trouble_code
	 * @param trouble_name
	 * @param trouble_remark
	 * @return
	 */
	 Result addTroubleInfo(String trouble_code,
								 String trouble_name, String trouble_remark);

	/**
	 * 返回所有异常信息
	 * @return
	 */
	 Result troubleList();

	/**
	 * 维修 未维修 状态
	 * @param id
	 * @return
	 */
	 Result changStatus(String id);
}

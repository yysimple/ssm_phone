package com.jxkj.service;


import com.jxkj.model.Result;

public interface PartsInfoService {
	/**
	 * 购买零件
	 * @param id
	 * @return
	 */
	Result buyParts(String id);

	/**
	 * 使用零件
	 * @param id
	 * @return
	 */
	Result useParts(String id);

	/**
	 * 查找所有的零件
	 * @return
	 */
	Result findAllParts();
}

package com.jxkj.dao;


import com.jxkj.model.PartsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartsInfoDao {
	//购买零件，+1
	void buyParts(String id);

	//使用零件，-1
	void useParts(String id);

	//查找所有的零件
	List<PartsInfo> findAllParts();
}

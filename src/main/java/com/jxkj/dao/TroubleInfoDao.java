package com.jxkj.dao;


import com.jxkj.model.TroubleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TroubleInfoDao {
	//添加损坏信息
	 void addTroubleInfo(TroubleInfo ti);
	
	//查看所有损坏信息
	 List<TroubleInfo> troubleList();
	
	//改变其状态
	 void changStatus(@Param("id") String id, @Param("status") Integer status);
	
	//通过id定位某一维修订单
	 TroubleInfo findTrouble(String id);
}

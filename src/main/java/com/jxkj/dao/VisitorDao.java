package com.jxkj.dao;



import com.jxkj.model.Visitor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorDao {
	/**
	 * 添加一个新的浏览记录
	 * @param v
	 */
	void addVisitor(Visitor v);

	/**
	 * 自动刷新一条记录，将时间最早的记录删除掉
	 * @return
	 */
	void delVisitor();

	/**
	 * 返回所有的记录
	 * @return
	 */
	List<Visitor> visitorList();
}

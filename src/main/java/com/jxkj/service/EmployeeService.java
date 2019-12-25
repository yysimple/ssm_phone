package com.jxkj.service;

import com.jxkj.model.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * 获取字符串
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 对hash进行操作
     * @param employee
     */
    void add(Employee employee);

    /**
     * 查找一个对象
     * @param id
     * @return
     */
    Employee selectById(String id);

    /**
     * 对list进行操作
     */
    void listAdd();

    /**
     * 查找一个对象
     */
    List<String> listRange(int currentPage, int pageSize);

}

package com.jxkj.test;

import com.jxkj.model.Employee;
import com.jxkj.service.EmployeeService;
import com.jxkj.service.UserService;
import com.jxkj.service.impl.EmployeeServiceImpl;
import com.jxkj.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class RedisTemplateTest {
    @Test
    public void testString1() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring.xml", "spring_redis.xml");
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        String key = "test";
        String res = employeeService.getString(key);
        System.out.println(res);
    }

    @Test
    public void testHash1() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring.xml", "spring_redis.xml");
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        Employee employee = new Employee();
        employee.setId("3");
        employee.setName("zaa");
        employee.setPassword("13465111");
        employee.setAge(18);

        employeeService.add(employee);
    }

    @Test
    public void testHash2() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring.xml", "spring_redis.xml");
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        Employee employee = employeeService.selectById("3");
        System.out.println(employee);
    }

    @Test
    public void testList1() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring.xml", "spring_redis.xml");
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        employeeService.listAdd();

        int currentPage = 2;
        int pageSize = 3;

        List<String> list = employeeService.listRange(currentPage,pageSize);

        for (String s : list) {
            System.out.println("这是获取到的第" + currentPage + "页新闻：" + s);
        }
    }

    @Test
    public void testList2() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("spring.xml", "spring_redis.xml");
        EmployeeServiceImpl e = context.getBean(EmployeeServiceImpl.class);

        /**
         * 模拟订单流程
         */

        String cardId = "1001";

        //1. 用户下单，完成队列
        //e.listQueueInit(cardId);
        System.out.println("-------------dadawuliu----------------");
        System.out.println("---------- 当前待执行的队列： -------------");
        List<String> listQueueWait = e.listQueueWait(cardId);
        for(String s : listQueueWait){
            System.out.println(s);
        }

        System.out.println("----------- 小哥开始运输 -----------");
        e.listQueueTouch(cardId);

        System.out.println("------------小哥操作后，待执行的队列------------");
        List<String> listQueueWait1 = e.listQueueWait(cardId);
        for(String s : listQueueWait1){
            System.out.println(s);
        }

        System.out.println("---------- 完成的队列： -------------");
        List<String> listQueueSucc = e.listQueueSucc(cardId);
        for(String s : listQueueSucc){
            System.out.println(s);
        }




    }

}

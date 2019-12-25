package com.jxkj.test;

import com.jxkj.dao.UserDao;
import com.jxkj.model.Result;
import com.jxkj.model.User;
import com.jxkj.service.UserService;
import com.jxkj.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


public class UserTest {

    @Test
    public void testDataSources() throws SQLException {
        ApplicationContext ac = null;
        {
            ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
        }
        DataSource dataSource=ac.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}

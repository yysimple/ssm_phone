package com.jxkj.service.impl;

import com.jxkj.model.Employee;
import com.jxkj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    HashOperations<String, String, Employee> hash;

    @Resource(name = "redisTemplate")
    ListOperations<String, String> list;

    /**
     * 查找string类型的 值，根据key
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        //设置过期时间
        redisTemplate.opsForValue().set("test", "这是一个测试数据", 2, TimeUnit.HOURS);

        if (redisTemplate.hasKey(key)) {
            //在redis中取出
            System.out.println("在redis中取出并返回:");
            return valueOperations.get(key);
        } else {
            //查询数据库
            String result = "RedisTemplate模板练习";

            valueOperations.set(key, result);
            System.out.println("在mysql中取出并返回:");
            return result;
        }
    }

    /**
     * 添加一个hash
     *
     * @param employee
     */
    @Override
    public void add(Employee employee) {
        System.out.println("--hash--");
        hash.put("employee", employee.getId(), employee);
    }

    /**
     * 通过id找到对应的对象
     *
     * @param id
     * @return
     */
    @Override
    public Employee selectById(String id) {
        boolean b = redisTemplate.opsForHash().hasKey("employee", id);
        if (b) {
            Employee employee = (Employee) redisTemplate.opsForHash().get("employee", id);
            System.out.println("redis中查询出来的...");
            return employee;
        } else {
            //如果不存在，从数据库中查询复制给Redis
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName("admin");
            employee.setPassword("1564");
            employee.setAge(20);
            redisTemplate.opsForHash().put("employee", employee.getId(), employee);
            System.out.println("mysql中查询出来的...");
            return employee;
        }
    }

    @Override
    public void listAdd() {
        String key = "news:top10";
        list.rightPushAll(key, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        System.out.println("新闻top10...");
    }

    @Override
    public List<String> listRange(int currentPage, int pageSize) {
        String key = "news:top10";
        //开始的位置
        int start = (currentPage - 1) * pageSize;
        //结束的位置
        int stop = pageSize * currentPage - 1;
        List<String> list1 = list.range(key, start, stop);
        return list1;
    }


    /**
     * -----  订单流程 -----
     */

    /**
     * 1. 付完款之后，会根据用户提供的信息，生成一个订单队列
     */

    public void listQueueInit(String cardId){
        String key = "prod:" + cardId;//初始化的key 待有多少个任务要完成

        list.leftPushAll(key,"1.商家出货","2.小哥发件","3.南昌某小区===》机场",
                "4.南昌机场===》上海机场","5.机场===》仓库","6.仓库===》用户");
    }

    /**
     * 2. 触发事件
     */
    public void listQueueTouch(String cardId){
        String key = "prod:" + cardId + ":succ";//已完成的队列
        // 将上面队列的最开始步骤放到这个队列的头部，实时跟踪快递
        list.rightPopAndLeftPush("prod:" + cardId, key);
    }

    /**
     * 3. 查询：客户查询快递到哪了
     */

    public List<String> listQueueSucc(String cardId){
        String key = "prod:" + cardId + ":succ";
        return list.range(key, 0, -1);
    }

    /**
     * 4. 物流查询: 查询物流还有多少任务没有完成
     */

    public List<String> listQueueWait(String cardId){
        String key = "prod:" + cardId;
        return list.range(key, 0, -1);
    }
}

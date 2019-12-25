package com.jxkj.test;

import com.jxkj.model.Employee;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class JedisTest {
    /**
     * java端 通过 Jedis 操作redis服务器
     * @param args
     */
    public static void main(String[] args) {
        String host = "192.168.159.128";
        int port = 6379;
        Jedis jedis = new Jedis(host,port);
        jedis.auth("123456");
    }

    /**
     * 测试字符串string
     */
    @Test
    public void testString1(){
        //获得redis的连接
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("strName","我是字符串");
        System.out.println(jedis.get("strName"));
        JedisPoolUtils.close(jedis);
    }

    /**
     * Redis的作用：缓解mysql的压力：
     * 需求：
     *      判断某key是否存在，如果存在直接在redis中查询
     *      如果不存在，先去MySQL中查询，然后吧结果存入到redis中
     */
    @Test
    public void testString2(){
        //获得redis的连接
        Jedis jedis = new Jedis("192.168.159.128",6379);
        jedis.auth("123456");
        // 判断 resKey 这个可以是否存在，存在直接返回给程序
        if(jedis.exists("resKey1")){
            System.out.println("这是redis中的数据：" + jedis.get("resKey1"));
        }else {
            // 弱不存在改 key 就从数据库里去查询改key 然后保存到redis中
            String resValue = "我是数据";
            jedis.set("resKey1",resValue);
            System.out.println("这是mysql中的数据：" + resValue);
        }
        jedis.close();
    }

    /**
     * 向redis里面读取一个user对象，存一个user对象
     */

    @Test
    public void testHash1(){
        //直接从redis中读取数据
        Jedis jedis = JedisPoolUtils.getJedis();
        if(jedis.exists("user")){
            Map<String,String> map = jedis.hgetAll("user");
            String id = jedis.hget("user","id");
            String name = jedis.hget("user","name");
            String password = jedis.hget("user","password");
            String type = jedis.hget("user","type");
            String status = jedis.hget("user","status");
            System.out.println("这是redis中查找到的数据：");
            System.out.println(id + "\t"+ name + "\t"+ password + "\t"+ type + "\t"+ status + "\t");
            System.out.println("通过hgetAll方法返回的map：" + map);
        }else {
            //数据中的数据
            String id = "1";
            String name = "zs";
            String password = "123456";
            String type = "1";
            String status = "1";
            jedis.hset("user","id",id);
            jedis.hset("user","name",name);
            jedis.hset("user","password",password);
            jedis.hset("user","type",type);
            jedis.hset("user","status",status);
            System.out.println("这是mysql中的数据：");
            System.out.println(id + "\t"+ name + "\t"+ password + "\t"+ type + "\t"+ status + "\t");
        }
    }

    @Test
    public void testHash2(){
        Jedis jedis = JedisPoolUtils.getJedis();
        String id = "10";
        String employee = "employee"+id;
        if(jedis.exists(employee)){
            //直接从redis中读取数据
            Map<String, String> map = jedis.hgetAll(employee);

            Employee e = new Employee();
            e.setId(map.get("id"));
            e.setName(map.get("name"));
            e.setPassword(map.get("password"));
            e.setAge(Integer.parseInt(map.get("age")));
            System.out.println("这是redis中的数据：" + e);
        }else {
            Employee employee1 = new Employee();
            employee1.setId("10");
            employee1.setName("ls");
            employee1.setPassword("123");
            employee1.setAge(18);
            Map<String, String> map = new HashMap<>();
            map.put("id",employee1.getId());
            map.put("name",employee1.getName());
            map.put("password",employee1.getPassword());
            map.put("age",employee1.getAge() + "");
            System.out.println("这是数据库中的数据：" + map);
            jedis.hmset(employee,map);
        }
    }
}

package com.jxkj.test;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 连接池的工具类
 */
public class JedisPoolUtils {

    public static JedisPool JEDIS_POOL;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(1);

        JEDIS_POOL = new JedisPool(poolConfig,"192.168.159.128",6379);

    }

    public static Jedis getJedis(){
        Jedis jedis = JEDIS_POOL.getResource();
        jedis.auth("123456");
        return jedis;
    }

    public static void close(Jedis jedis){
        jedis.close();
    }
}

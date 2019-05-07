package com.study.highconcurrent.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Redis客户端
 *
 * @author Administrator
 * @version V1.0
 */
@Component
public class RedisClient {
    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    /**
     * set方法
     *
     * @param key
     * @param val
     * @throws Exception
     */
    public void set(String key, String val) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, val);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * get方法
     *
     * @param key
     * @throws Exception
     */
    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}

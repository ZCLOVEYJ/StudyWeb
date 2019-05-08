package com.mmall.common;

import com.mmall.utils.PropertiesUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    //jedis 连接池
    private static JedisPool pool;
    //最大连接数
    private static Integer maxTotal = Integer.parseInt(PropertiesUtils.getProperty("redis.max.total", "20"));
    //在jedis中最大的idle状态(空闲的)的jedis实例个数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtils.getProperty("redis.max.idle", "10"));
    //在jedis中最小的idle状态(空闲的)的jedis实例个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtils.getProperty("redis.min.idle", "2"));
    //在borrow一个jedis实例的时候，是否要进行验证，如果赋值true，则得到的jedis实例时可以用的
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtils.getProperty("redis.test.borrow", "true"));
    //在return一个jedis实例的时候，是否要进行验证，如果赋值true，则放回redispool的jedis实例时可以用的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtils.getProperty("redis.test.return", "false"));


    private static String redisIp = PropertiesUtils.getProperty("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtils.getProperty("redis.port"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        config.setBlockWhenExhausted(true);//连接耗尽的时候是否阻塞，false抛出异常，true阻塞直到超时.默认true

        pool = new JedisPool(config, redisIp, redisPort, 1000 * 2);
    }

    static {
        initPool();
    }

    public static Jedis getJedis() {

        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        pool.returnResource(jedis);

    }

    public static void returnBrokenResource(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("chen", "chen");
        returnResource(jedis);

        pool.destroy();//临时调用

        System.out.println("Program is end");
    }

}

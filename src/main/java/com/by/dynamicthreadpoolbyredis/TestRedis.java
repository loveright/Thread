package com.by.dynamicthreadpoolbyredis;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName TestRedis
 * @description: 测试redis发布订阅模式，计划根据redis发布订阅实现动态线程池
 * @date 2023/5/13 22:19
 */
public class TestRedis {

    private static RedisTemplate redisTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
//        redisTemplate.opsForValue().set("b",1);
    }
}

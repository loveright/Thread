package com.by.testredis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName TestRedis
 * @description: TODO
 * @date 2023/5/13 21:31
 */
public class TestRedis {

    RedisTemplate redisTemplate;

    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

    }
    @Test
    public void test1() {
        redisTemplate.opsForValue().set("aa","1");
    }
}

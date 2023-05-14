package com.by.dynamicthreadpoolbyredis.publish;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName RedisPublish
 * @description: TODO
 * @date 2023/5/14 17:34
 */
public class RedisPublish {

    @Autowired
    static RedisTemplate redisTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
//        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(Object.class));
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        redisTemplate.convertAndSend("ITCAST",map);
    }
}

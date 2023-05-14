package com.by.dynamicthreadpoolbyredis.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.lang.reflect.Type;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName RedisMessageListener
 * @description: 消息监听器：需要实现MessageListener接口,实现onMessage方法
 * @date 2023/5/13 22:39
 */
public class RedisMessageListener implements MessageListener {

    /**
     * 	处理redis消息：当从redis中获取消息后，打印主题名称和基本的消息
     */
    public void onMessage(Message message, byte[] pattern) {

        String s = new String(message.getBody());

        System.out.println("从channel为" + new String(message.getChannel())
                + "中获取了一条新的消息,消息内容:" +s);

        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("key1"));
        System.out.println(jsonObject.get("key2"));

    }

}


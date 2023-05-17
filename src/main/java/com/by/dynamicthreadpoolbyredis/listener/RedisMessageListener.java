package com.by.dynamicthreadpoolbyredis.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName RedisMessageListener
 * @description: 消息监听器：需要实现MessageListener接口,实现onMessage方法
 * @date 2023/5/13 22:39
 */
@Component
public class RedisMessageListener implements MessageListener {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 	处理redis消息：当从redis中获取消息后，打印主题名称和基本的消息
     */
    public void onMessage(Message message, byte[] pattern) {

        String s = new String(message.getBody());

        System.out.println("从channel为" + new String(message.getChannel())
                + "中获取了一条新的消息,消息内容:" +s);

        JSONObject jsonObject = JSON.parseObject(s);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = applicationContext.getBean("threadPoolExecutor", ThreadPoolTaskExecutor.class);

        System.out.println("threadPoolExecutor" + " 重新配置 核心线程数: " + jsonObject.get("coreSize") + " 最大线程数 ：" + jsonObject.get("maxSize"));
        threadPoolTaskExecutor.setCorePoolSize((Integer) jsonObject.get("coreSize"));
        threadPoolTaskExecutor.setMaxPoolSize((Integer) jsonObject.get("maxSize"));
        System.out.println(threadPoolTaskExecutor.getCorePoolSize());
        System.out.println(threadPoolTaskExecutor.getMaxPoolSize());
    }

}


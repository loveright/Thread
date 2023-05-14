package com.by.dynamicthreadpool.controller;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName TestController
 * @description: 测试根据nocos实现的动态线程池
 * @date 2023/5/10 23:36
 */
@RestController
public class TestController {

    @Resource
    private ThreadPoolTaskExecutor orderThreadPool;

    @Resource
    private ThreadPoolTaskExecutor smsThreadPool;

    @RequestMapping("/test")
    public String test() {
        StringBuilder sb = new StringBuilder();

        orderThreadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " 执行任务");
        });


        smsThreadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " 执行任务");
        });

        sb.append("orderThreadPool ");
        sb.append(" 核心线程数 " + orderThreadPool.getCorePoolSize());
        sb.append(" 最大线程数 " + orderThreadPool.getMaxPoolSize());
        sb.append("<br />");

        sb.append("smsThreadPool ");
        sb.append(" 核心线程数 " + smsThreadPool.getCorePoolSize());
        sb.append(" 最大线程数 " + smsThreadPool.getMaxPoolSize());

        return sb.toString();
    }

}

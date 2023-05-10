package com.by.dynamicthreadpool.threadpool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName DynamicThreadPoolExecutor
 * @description: 封装线程池
 * @date 2023/5/10 23:30
 */
public class DynamicThreadPoolExecutor extends ThreadPoolTaskExecutor {


    // 队列最大长度
    private int queueCapacity = 1000;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 300;

    public DynamicThreadPoolExecutor(int corePoolSize, int maxPoolSize, String threadNamePrefix) {
        this.setMaxPoolSize(maxPoolSize);
        this.setCorePoolSize(corePoolSize);
        this.setQueueCapacity(queueCapacity);
        this.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        this.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程名称前缀
        this.setThreadNamePrefix(threadNamePrefix + "-");
    }
}

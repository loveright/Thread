package com.by.newSingleThreadScheduled;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

public class SpringThreadPoolTaskExecutor {

    public void runTask() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /**配置核心线程数*/
        executor.setCorePoolSize(3);
        /**配置最大线程数*/
        executor.setMaxPoolSize(6);
        // 非核心线程存活时间
        executor.setKeepAliveSeconds(30);
        /**配置队列大小*/
        executor.setQueueCapacity(10);
        /**等待任务在关机时完成--表明等待所有线程执行完*/
        executor.setWaitForTasksToCompleteOnShutdown(true);
        /** 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止*/
        executor.setAwaitTerminationSeconds(30);
        /**配置线程池中的线程的名称前缀*/
        executor.setThreadNamePrefix("test-async-thread-");
        /**rejection-policy：当pool已经达到max size的时候，如何处理新任务(CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行)*/
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        /**初始化执行器*/
        executor.initialize();
    }
}

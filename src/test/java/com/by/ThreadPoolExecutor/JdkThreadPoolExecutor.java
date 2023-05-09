package com.by.ThreadPoolExecutor;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName TestThread
 * @description: TODO
 * @date 2023/5/8 22:08
 */
public class JdkThreadPoolExecutor {


    // 直接提交队列，拒绝策略为AbortPolic策略，直接抛出异常
    @Test
    public void runTask1() {
        Runnable task = ()->{
            System.out.println(Thread.currentThread().getName());
        };

        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new SynchronousQueue<>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            threadExecutor.execute(task);
        }
    }

    // 有界任务队列，最大线程数+有界队列容量=可执行最大任务数
    @Test
    public void runTask2() {
        Runnable task = ()->{
            System.out.println(Thread.currentThread().getName());
        };

        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 4; i++) {
            threadExecutor.execute(task);
        }
    }

    // 无界任务队列，此时maximumPoolSize这个参数是无效的
    @Test
    public void runTask3() {
        Runnable task = ()->{
            int priority;
            System.out.println(Thread.currentThread().getName());
        };

        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 6; i++) {
            threadExecutor.execute(task);
        }
    }

    // 优先任务队列，此时maximumPoolSize这个参数是无效的
    @Test
    public void runTask4() {
//        Runnable task = ()->{
//            System.out.println(Thread.currentThread().getName());
//        };

        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            threadExecutor.execute(new ThreadTask(i));
        }
    }
}




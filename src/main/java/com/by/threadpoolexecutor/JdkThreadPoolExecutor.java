package com.by.threadpoolexecutor;

import java.util.concurrent.*;

public class JdkThreadPoolExecutor {


    Runnable task1 = ()->{
        System.out.println(Thread.currentThread().getName());
    };

    // 直接提交队列，拒绝策略为AbortPolic策略，直接抛出异常
    public void runTask1() {
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new SynchronousQueue<>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            threadExecutor.execute(task1);
        }
    }



    // 有界任务队列
    public void runTask2() {
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            threadExecutor.execute(task1);
        }
    }

    public static void main(String[] args) {
//        JdkThreadPoolExecutor jdkThreadPoolExecutor = new JdkThreadPoolExecutor();
//        jdkThreadPoolExecutor.runTask1();
        // 优先任务队列
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        for(int i=0;i<10;i++) {
            threadExecutor.execute(new ThreadTask(i));
        }

    }
}

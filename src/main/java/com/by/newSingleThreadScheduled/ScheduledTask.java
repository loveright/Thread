package com.by.newSingleThreadScheduled;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class ScheduledTask implements Runnable{

    private static int i = 0;

    // 创建任务
    public static Runnable task = ()->{
        System.out.println(i++);
    };

    // 执行任务
    public void runTask(Runnable task) {
        ScheduledExecutorService taskExecutor = Executors.newSingleThreadScheduledExecutor();
        taskExecutor.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
    }

    // 多线程执行
    public void runConcurrentTask() {
        //手动创建线程池
       /*
        corePoolSize：核心线程数，也是线程池中常驻的线程数，线程池初始化时默认是没有线程的，当任务来临时才开始创建线程去执行任务
        maximumPoolSize：最大线程数，在核心线程数的基础上可能会额外增加一些非核心线程，需要注意的是只有当workQueue队列填满时才会创建多于corePoolSize的线程(线程池总线程数不超过maxPoolSize)
        keepAliveTime：非核心线程的空闲时间超过keepAliveTime就会被自动终止回收掉，注意当corePoolSize=maxPoolSize时，keepAliveTime参数也就不起作用了(因为不存在非核心线程)；
        unit：keepAliveTime的时间单位
        workQueue：用于保存任务的队列，可以为无界、有界、同步移交new SynchronousQueue<>()三种队列类型之一，当池子里的工作线程数大于corePoolSize时，这时新进来的任务会被放到队列中
        threadFactory：创建线程的工厂类，默认使用Executors.defaultThreadFactory()，也可以使用guava库的ThreadFactoryBuilder来创建
        handler：线程池无法继续接收任务(队列已满且线程数达到maximunPoolSize)时的饱和策略，取值有AbortPolicy、CallerRunsPolicy、DiscardOldestPolicy、DiscardPolicy
        */
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(3, 6, 30, TimeUnit.SECONDS,
                new SynchronousQueue<>());


    }
    @Override
    public void run() {
        System.out.println(i++);
    }

    public static void main(String[] args) {
        ScheduledTask t = new ScheduledTask();
        t.runTask(t);
    }
}

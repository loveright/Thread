package com.by.rejectpolicy;

import java.util.concurrent.*;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName ThreadPool
 * @description: TODO
 * @date 2023/5/9 21:18
 */
public class ThreadPool {

    private static ExecutorService pool;

    Runnable task = ()->{
        try {
            //让线程阻塞，使后续任务进入缓存队列
            Thread.sleep(1000);
            System.out.println("ThreadName:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };

    public void runTask() {

        //自定义拒绝策略
        pool = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString()+"执行了拒绝策略");

            }
        });

        for(int i=0;i<10;i++) {
            pool.execute(task);
        }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.runTask();
    }
}

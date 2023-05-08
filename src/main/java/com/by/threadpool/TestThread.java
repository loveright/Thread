package com.by.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class TestThread {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<10;i++){
//                    System.out.println(Thread.currentThread().getName());
//                }
//            }
//        }).start();
        //----------------------------------------------
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        //ExecutorService threadPool = Executors.newFixedThreadPool(1);
//        for(int i=0;i<10;i++){
//            TestThreadRunnable testThreadRunnable = new TestThreadRunnable();
//            threadPool.execute(testThreadRunnable);
//        }
//        threadPool.shutdown();
        //----------------------------------------------
        Thread thread = null;
        TestThreadRunnable testThreadRunnable = new TestThreadRunnable();
        for(int i=0;i<10;i++){
            thread = new Thread(testThreadRunnable);

        }
        thread.start();


    }
}

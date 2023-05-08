package com.by.threadpool;

public class TestThreadRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

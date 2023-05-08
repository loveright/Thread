package com.by.threadpool;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable{

    private List<Map.Entry<Integer, String>> list;
    private CountDownLatch begin;
    private CountDownLatch end;

    //创建个构造函数初始化 list,和其他用到的参数
    public MyThread(List<Map.Entry<Integer, String>> list, CountDownLatch begin, CountDownLatch end) {
        this.list = list;
        this.begin = begin;
        this.end = end;
    }
    @Override
    public void run() {
        //执行完让线程直接进入等待
        try {
            for (int i = 0; i < list.size(); i++) {
                //这里还要说一下，，由于在实质项目中，当处理的数据存在等待超时和出错会使线程一直处于等待状态
                //这里只是处理简单的，
                //分批 批量插入
                Map.Entry<Integer, String> entry = list.get(i);
                System.out.println(Thread.currentThread().getName()+": "+list.get(i).getValue());
            }
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //这里要主要了，当一个线程执行完了计数要减一,不然这个线程会被一直挂起
            // ，end.countDown()，这个方法就是直接把计数器减一的
            end.countDown();
        }
    }
}

package com.by.sourceconflict;

public class SynchronizedDemo {
    public static void main(String[] args) {
        //实例化4个售票员，用4个线程模拟4个售票员
        Runnable r = ()->{
            while (TicketCenter.restCount > 0){
                //对象锁
                //类锁
                //不同的线程要保证遇到的是同一把锁
                //同步代码段
                synchronized (""){
                    if(TicketCenter.restCount <= 0){
                        return;
                    }
                    System.out.println(Thread.currentThread().getName()+"卖出一张票剩余："+ --TicketCenter.restCount+"张");
                }
            }
        };
        //四个线程模拟4个售票员
        Thread t1 = new Thread(r,"thread-1");
        Thread t2 = new Thread(r,"thread-2");
        Thread t3 = new Thread(r,"thread-3");
        Thread t4 = new Thread(r,"thread-4");

        //开启线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

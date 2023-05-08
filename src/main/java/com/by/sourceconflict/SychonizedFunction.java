package com.by.sourceconflict;

public class SychonizedFunction {
    public static void main(String[] args) {
        //实例化4个售票员，用4个线程模拟4个售票员
        Runnable r = ()->{
            while (TicketCenter.restCount > 0){
                //对象锁
                //类锁
                //不同的线程要保证遇到的是同一把锁
                //同步方法
//                soldTicket();
                soldTicket1();
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

    private static void soldTicket(){
        synchronized (""){
            if(TicketCenter.restCount <= 0){
                return;
            }
            System.out.println(Thread.currentThread().getName()+"卖出一张票剩余："+ --TicketCenter.restCount+"张");
        }
    }
    /*
    同步的方法
    静态方法：同步方法就是类锁，当前类.class
    非静态方法：同步锁，this
    * */
    private synchronized static void soldTicket1(){
        if(TicketCenter.restCount <= 0){
            return;
        }
        System.out.println(Thread.currentThread().getName()+"卖出一张票剩余："+ --TicketCenter.restCount+"张");
    }
}

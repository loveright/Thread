package com.by.thread;

public class ThreadMethod {
    public static void main(String[] args) {
//       threadSleep();
//        threadPriority();
        threadYield();
    }
    //线程的礼让
    private static void threadYield(){
        //线程礼让，指的是让当前运行状态的线程释放自己的cpu资源，由运行状态，回到就绪状态。
        Runnable r = ()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+ ":" +i);
                if(i == 3){
                    Thread.yield();
                }
            }
        };
        Thread t1 = new Thread(r,"Thread-1");
        Thread t2 = new Thread(r,"Thread-2");
        t1.start();
        t2.start();
    }
    //设置线程优先级
    private static void threadPriority(){
        //设置线程的优先级只是修改这个线程可以去抢CPU时间片的概率
        //并不是优先级高的线程一定能抢到时间片
        //优先级的设置，是一个整数，[0，10]，默认为5
        Runnable r = ()->{
          for(int i=0;i<100;i++){
              System.out.println(Thread.currentThread().getName()+":"+i);
          }
        };
        Thread t1 = new Thread(r,"Thread-1");
        Thread t2 = new Thread(r,"Thread-2");
        //设置优先级
        t1.setPriority(10);
        t2.setPriority(1);
        t1.start();
        t2.start();
    }
    private static void threadSleep(){
        //1.实例化一个线程对象
        MyThread2 t = new MyThread2();
        t.start();
    }
    private static void threadName(){
        //线程的命名
        Thread t = new Thread();
        t.setName("custom");
        System.out.println(t.getName());
        //实例化线程的同时进行命名
        Thread t2 = new Thread(()->{},"custom2");
        System.out.println(t2.getName());
        //使用自定义线程类
        MyThread2 t3 = new MyThread2("custom3");
        System.out.println(t3.getName());
    }
}
class MyThread2 extends Thread   {
    public MyThread2(){
    }
    public MyThread2(String name){
        this.setName(name);
        //super(name);
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println(i);
            try {
                //线程休眠时是由运行态变为阻塞态，休眠时间到了后由阻塞态变为就绪态
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

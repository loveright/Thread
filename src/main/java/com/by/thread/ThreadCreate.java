package com.by.thread;

public class ThreadCreate {
    public static void main(String[] args) {
        //线程实例化对应新生态
        MyThread mt = new MyThread();
        //线程执行start方法后进入就绪态
        //直接调用run方法线程不会进入就绪状态
        mt.start();

        //通过runnable接口
        Runnable r = ()->{
            for(int i=0;i<10;i++){
                System.out.println("线程2的逻辑："+ i);
            }
        };
        Thread t2 = new Thread(r);
        t2.start();
        System.out.println("主线程的执行逻辑结束了");
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("子线程中的逻辑："+i);
        }
    }
}

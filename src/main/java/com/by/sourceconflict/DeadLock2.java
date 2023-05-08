package com.by.sourceconflict;

public class DeadLock2 {
    public static void main(String[] args) {
        //死锁：多个线程彼此持有对方的锁对象，而不释放自己的锁。
        //wait:等待，是Object类中的一个方法，当前的线程释放自己的锁标记，并且让出cpu资源，使当前线程进入等待队列。
        //notify:通知，是Object类中的一个方法，唤醒等待队列中的一个线程，使这个线程进入锁池。
        //notifyAll:通知，是Object类中的一个方法，唤醒等待队列中的所有线程(A.notifyAll则唤醒等待A的所有线程)，并使这些线程进入锁池。
        Runnable runnable1 = ()->{
            synchronized ("A"){
                System.out.println("A线程持有了A锁，等待B锁");
                try {
                    //A线程释放已经持有的A锁标记，并进入等待队列
                    "A".wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized ("B"){
                    System.out.println("A线程同时持有了A锁和B锁");
                }
            }
        };

        Runnable runnable2 = ()->{
            synchronized ("B"){
                System.out.println("B线程持有了B锁，等待A锁");
                synchronized ("A"){
                    System.out.println("B线程同时持有了A锁和B锁");
                    "A".notifyAll();
                }
            }
        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();
    }
}

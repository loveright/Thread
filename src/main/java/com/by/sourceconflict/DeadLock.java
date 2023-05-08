package com.by.sourceconflict;

public class DeadLock {
    public static void main(String[] args) {
        //死锁：多个线程彼此持有对方的锁对象，而不释放自己的锁
        Runnable runnable1 = ()->{
          synchronized ("A"){
              System.out.println("A线程持有了A锁，等待B锁");
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
                }
            }
        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();
    }
}

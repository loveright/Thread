package com.by.alternateprint;

public class DoTest2 {
    private volatile int COUNT_NUM = 1;
    private volatile String CODE = "A";
    private static int oneTimes = 34;
    private static int othersTimes = 33;

    void onePrint() {
        synchronized (this) {
            while(!CODE.equals("A")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + COUNT_NUM);
            COUNT_NUM++;
            CODE = "B";
            notifyAll();
        }
    }
    void twoPrint() {
        synchronized (this) {
            while(!CODE.equals("B")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + COUNT_NUM);
            COUNT_NUM++;
            CODE = "C";
            notifyAll();
        }
    }
    void threePrint() {
        synchronized (this) {
            while(!CODE.equals("C")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + COUNT_NUM);
            COUNT_NUM++;
            CODE = "A";
            notifyAll();
        }
    }


    public static void main(String[] args) {
        DoTest2 printNumber = new DoTest2();
        new Thread(() -> {
            for (int i = 0; i < oneTimes; i++) {
                printNumber.onePrint();
            }
        },"线程A").start();

        new Thread(() -> {
            for (int i = 0; i < othersTimes; i++) {
                printNumber.twoPrint();
            }
        },"线程B").start();

        new Thread(() -> {
            for (int i = 0; i < othersTimes; i++) {
                printNumber.threePrint();
            }
        },"线程C").start();
    }
}

package com.by.alternateprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DoTest1 {

    //控制三个线程 ABC，保证同一时刻只有一个线程工作
    private static Lock lock = new ReentrantLock(true);

    //  Condition ,控制等待或是 通知
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    //默认的通知 暗号 A
    private static String CODE = "A";

    //设置初始打印值
    private static int COUNT_NUM = 1;

    //线程是否需要终止 标记
    private static volatile boolean IS_INTERRUPT = false;

    public static void main(String[] args) {

        Thread A = new Thread(() -> {
            while (!IS_INTERRUPT) {
                try {
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread B = new Thread(() -> {
            while (!IS_INTERRUPT) {
                try {
                    printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread C = new Thread(() -> {
            while (!IS_INTERRUPT) {
                try {
                    printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        A.start();
        B.start();
        C.start();
    }

    public static void printA() throws InterruptedException {

        //等待
        lock.lock();
        try {
            if (COUNT_NUM >= 100) {
                IS_INTERRUPT = true;
                return;
            }
            //核对
            while (!CODE.equals("A")) {
                //暗号不对，就进入等待
                conditionA.await();
            }
            System.out.println("A, count" + COUNT_NUM);
            //改暗号，通知B
            CODE = "B";
            COUNT_NUM = COUNT_NUM + 1;
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void printB() throws InterruptedException {
        lock.lock();
        try {

            if (COUNT_NUM >= 100) {
                IS_INTERRUPT = true;
                return;
            }

            while (!CODE.equals("B")) {
                conditionB.await();
            }

            System.out.println("B, count" + COUNT_NUM);
            //改暗号，通知C
            CODE = "C";
            COUNT_NUM = COUNT_NUM + 1;

            conditionC.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public static void printC() throws InterruptedException {
        lock.lock();
        try {

            if (COUNT_NUM >= 100) {
                IS_INTERRUPT = true;
                return;
            }

            while (!CODE.equals("C")) {
                conditionC.await();
            }

            System.out.println("C, count" + COUNT_NUM);
            //改暗号，通知A
            CODE = "A";
            COUNT_NUM = COUNT_NUM + 1;
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }

    }

}

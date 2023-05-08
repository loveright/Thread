package com.by.alternateprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DoTest {

    //控制三个线程 ABC，保证同一时刻只有一个线程工作
    private static Lock lock = new ReentrantLock(true);

    //  Condition ,控制等待或是 通知
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    //默认的通知 暗号 A
    private static String  CODE= "A";


    public static void main(String[] args) {

        Thread A = new Thread(() -> {
            while (true) {
                try {
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread B = new Thread(() -> {
            while (true) {
                try {
                    printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread C = new Thread(() -> {
            while (true) {
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
            //核对
            while (!CODE.equals("A")) {
                //暗号不对，就进入等待
                conditionA.await();
            }
            System.out.println("A");
            //改暗号，通知B
            CODE = "B";
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void printB() throws InterruptedException {
        lock.lock();
        try {
            while (!CODE.equals("B")) {
                conditionB.await();
            }
            System.out.println("B");
            //改暗号，通知C
            CODE = "C";
            conditionC.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public static void printC() throws InterruptedException {
        lock.lock();
        try {
            while (!CODE.equals("C")) {
                conditionC.await();
            }
            System.out.println("C");

            //改暗号，通知A
            CODE = "A";
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }

    }


}

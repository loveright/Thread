package com.by.callback;

/**
 * 程序入口
 * @Author Tan
 */
public class Main {
    public static void main(String[] args) {

        // 定义一个回调
        Callback callback = new Callback() {
            @Override
            public void returnResult(String msg) {
                System.out.println("小明通知：" + msg);
            }
        };
        Me me = new Me();
        System.out.println("我准备买票----");
//        me.buyTicketsSyn(callback);           //发起买票（同步回调）
        me.buyTicketsSynASyn(callback);         //发起买票（异步回调）
        System.out.println("我在等待出票----");


    }
}


package com.by.callback;

/**
 * 售票员
 *
 * @Author Tan
 */
public class Conductor {

    public void printTickets(Callback callback) {

        System.out.println("售票员开始处理 . . . ");
        try {
            Thread.currentThread();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("售票员处理完毕 . . . ");

        // 出票完成，通知出票结果(这里是发起回调的地方)
        callback.returnResult("出票完成");
    }
}

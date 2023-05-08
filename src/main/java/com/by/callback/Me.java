package com.by.callback;

/**
 * 这是我
 * @Author Tan
 */
public class Me {

    // 指定一个售票人(待会他出票后要通知我)
    Conductor conductor = new Conductor();

    /*
     * 同步回调
     * 方法：买票
     * 内容：内容：把回调对象(小明)传给售票员
     */
    public void buyTicketsSyn(Callback callback) {
        conductor.printTickets(callback);
    }
    /*
     * 异步回调
     * 方法：买票
     * 内容：把回调对象(小明)传给售票员
     */
    public void buyTicketsSynASyn(Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                conductor.printTickets(callback);
            }
        }).start();
    }
}

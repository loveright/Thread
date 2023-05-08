package com.by.callback;

/**
 * ������
 * @Author Tan
 */
public class Me {

    // ָ��һ����Ʊ��(��������Ʊ��Ҫ֪ͨ��)
    Conductor conductor = new Conductor();

    /*
     * ͬ���ص�
     * ��������Ʊ
     * ���ݣ����ݣ��ѻص�����(С��)������ƱԱ
     */
    public void buyTicketsSyn(Callback callback) {
        conductor.printTickets(callback);
    }
    /*
     * �첽�ص�
     * ��������Ʊ
     * ���ݣ��ѻص�����(С��)������ƱԱ
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

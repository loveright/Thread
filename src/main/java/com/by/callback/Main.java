package com.by.callback;

/**
 * �������
 * @Author Tan
 */
public class Main {
    public static void main(String[] args) {

        // ����һ���ص�
        Callback callback = new Callback() {
            @Override
            public void returnResult(String msg) {
                System.out.println("С��֪ͨ��" + msg);
            }
        };
        Me me = new Me();
        System.out.println("��׼����Ʊ----");
//        me.buyTicketsSyn(callback);           //������Ʊ��ͬ���ص���
        me.buyTicketsSynASyn(callback);         //������Ʊ���첽�ص���
        System.out.println("���ڵȴ���Ʊ----");


    }
}


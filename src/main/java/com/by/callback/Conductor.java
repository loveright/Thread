package com.by.callback;

/**
 * ��ƱԱ
 *
 * @Author Tan
 */
public class Conductor {

    public void printTickets(Callback callback) {

        System.out.println("��ƱԱ��ʼ���� . . . ");
        try {
            Thread.currentThread();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("��ƱԱ������� . . . ");

        // ��Ʊ��ɣ�֪ͨ��Ʊ���(�����Ƿ���ص��ĵط�)
        callback.returnResult("��Ʊ���");
    }
}

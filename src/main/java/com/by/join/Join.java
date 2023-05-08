package com.by.join;

public class Join {
    static class ThreadA implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("�������̣߳���˯һ��");
                Thread.sleep(1000);
                System.out.println("�������̣߳���˯����һ��");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadA());
        thread.start();
        thread.join(); // ���뵽���߳��У������ͱ��˳��
        // �������߳����
        System.out.println("�������join�������һ��ȱ�����������˾Ͳ�һ����");
    }
}

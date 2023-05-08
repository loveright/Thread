package com.by.threadpool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest3 {


    public static void main(String[] args) {
        int k = 0;
        try {
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(2);
                k = i;
                System.out.println("----------: "+k);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

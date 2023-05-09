package com.by.threadpoolexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Task {

    /**定时任务
     * @param args
     */
    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            int i=1;
            public void run() {
                try{
                    System.out.println(i);
                    i++;
//                    if(i==5){
//                        int a = 1/0;
//                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            };
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 1表示时间单位的数值 TimeUnit.SECONDS  延时单位为秒
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }

}

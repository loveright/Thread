package com.by.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName ThreadTask
 * @description: TODO
 * @date 2023/5/8 22:52
 */
public class ThreadTask implements Runnable,Comparable<ThreadTask>{

    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ThreadTask() {

    }

    public ThreadTask(int priority) {
        this.priority = priority;
    }

    //当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1,值越小优先级越高
    public int compareTo(ThreadTask o) {
        return  this.priority>o.priority?-1:1;
    }

    public void run() {
        try {
            //让线程阻塞，使后续任务进入缓存队列
            TimeUnit.SECONDS.sleep(1);
            System.out.println("priority:"+this.priority+",ThreadName:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

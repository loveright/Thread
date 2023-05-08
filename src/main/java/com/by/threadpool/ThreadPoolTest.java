package com.by.threadpool;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void exec(List<Map.Entry<Integer, String>> list) throws InterruptedException{
        int count = 300;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<Map.Entry<Integer, String>> newlist = null;       //存放每个线程的执行数据
        //创建一个线程池，数量和开启线程的数量一样
        ExecutorService executor = Executors.newFixedThreadPool(runSize);
        //创建两个个计数器
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(runSize);

        //循环创建线程
        for (int i = 0; i < runSize ; i++) {
            //计算每个线程执行的数据
            if((i+1)==runSize){
                int startIndex = (i*count);
                int endIndex = list.size();
                newlist= list.subList(startIndex, endIndex);
            }else{
                int startIndex = (i*count);
                int endIndex = (i+1)*count;
                newlist= list.subList(startIndex, endIndex);
            }
            //线程类
            MyThread mythead = new MyThread(newlist,begin,end);
            //这里执行线程的方式是调用线程池里的executor.execute(mythead)方法。

            executor.execute(mythead);
        }

        begin.countDown();
        end.await();

        //执行完关闭线程池
        executor.shutdown();
    }

    public static void main(String[] args) {
        Map<Integer,String> map = new LinkedHashMap();
        List<Map.Entry<Integer,String>> list = new ArrayList<>();
        //数据越大线程越多
        for (int i = 0; i < 600; i++) {
            map.put(i,"a"+i);
        }
        for (Map.Entry<Integer,String> entry:map.entrySet()) {
            list.add(entry);
        }
        //List list =  Arrays.asList(map.entrySet().toArray());
        //System.out.println(Arrays.asList(map.entrySet().toArray()));
        //List list = new ArrayList((Collection) new LinkedHashMap());

        try {
            exec(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

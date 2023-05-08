package com.by.threadpool;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolTest2 {
    public static void exec(List<Map.Entry<Integer, String>> list) throws InterruptedException{
        int count = 3;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize != 0 && listSize%count == 0) ? listSize/count : listSize/count + 1;  //开启的线程数
        List<Map.Entry<Integer, String>> newlist = null; //存放每个线程的执行数据
        //创建一个线程池，数量和开启线程的数量一样
        ExecutorService executor = Executors.newFixedThreadPool(runSize);
        // 定义一个任务集合
        List<Future<Integer>> tasks = new ArrayList<Future<Integer>>();
        List<Callable<Integer>> taskCallables = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;

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
            final List<Map.Entry<Integer, String>> listStr = newlist;

            task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    /*System.out.println(Thread.currentThread().getName() + "线程：" + listStr);*/

                    for (int i = 0; i < listStr.size(); i++) {
                        if("a5".equals(listStr.get(i).getValue())){
                            TimeUnit.SECONDS.sleep(50);
                        }
                        Map.Entry<Integer, String> entry = list.get(i);
                        System.out.println(Thread.currentThread().getName()+": "+listStr.get(i).getValue());
                    }
                    return 1;
                }
            };
            taskCallables.add(task);
            Future<Integer> futureNow = executor.submit(task);
            tasks.add(futureNow);

        }

        for (Future<Integer> future : tasks) {
            try {
                Integer intRes = future.get(2, TimeUnit.SECONDS);
                System.out.println("result : " + intRes);
            } catch (InterruptedException ex) {
                System.out.println("子线程被中断 ");
            } catch (ExecutionException e) {
                System.out.println("子线程执行出错");
            } catch (TimeoutException e) {
                System.out.println("处理超时");
                executor.shutdownNow();

            } finally {
                System.out.println("-----finally--------");
                future.cancel(true); // 中断子线程
            }
        }
        //执行完关闭线程池
        executor.shutdown();
    }

    public static void main(String[] args) {
        Map<Integer,String> map = new LinkedHashMap();
        List<Map.Entry<Integer,String>> list = new ArrayList<>();
        //数据越大线程越多
        for (int i = 0; i < 9; i++) {
            map.put(i,"a"+i);
        }
        for (Map.Entry<Integer,String> entry:map.entrySet()) {
            list.add(entry);
        }

        try {
            exec(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

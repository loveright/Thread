package com.by.threadpool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolTest1 {
    public static void exec(List<Map.Entry<Integer, String>> list) throws InterruptedException{
        int count = 300;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<Map.Entry<Integer, String>> newlist = null; //存放每个线程的执行数据
        //创建一个线程池，数量和开启线程的数量一样
        ExecutorService executor = Executors.newFixedThreadPool(runSize);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
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
                        //这里还要说一下，，由于在实质项目中，当处理的数据存在等待超时和出错会使线程一直处于等待状态
                        //这里只是处理简单的，
                        //分批 批量插入
                        Map.Entry<Integer, String> entry = list.get(i);
                        System.out.println(Thread.currentThread().getName()+": "+listStr.get(i).getValue());
                    }
                    return 1;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }
        List<Future<Integer>> results = executor.invokeAll(tasks);
        //捕捉异常或者....  这句话很重要
        for (Future<Integer> res : results) {
            try {
                System.out.println(res.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
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

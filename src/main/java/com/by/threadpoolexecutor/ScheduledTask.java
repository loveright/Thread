package com.by.threadpoolexecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class ScheduledTask implements Runnable{

    private static int i = 0;

    // ��������
    public static Runnable task = ()->{
        System.out.println(i++);
    };

    // ִ������
    public void runTask(Runnable task) {
        ScheduledExecutorService taskExecutor = Executors.newSingleThreadScheduledExecutor();
        taskExecutor.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
    }

    // ���߳�ִ��
    public void runConcurrentTask() {
        //�ֶ������̳߳�
       /*
        corePoolSize�������߳�����Ҳ���̳߳��г�פ���߳������̳߳س�ʼ��ʱĬ����û���̵߳ģ�����������ʱ�ſ�ʼ�����߳�ȥִ������
        maximumPoolSize������߳������ں����߳����Ļ����Ͽ��ܻ��������һЩ�Ǻ����̣߳���Ҫע�����ֻ�е�workQueue��������ʱ�Żᴴ������corePoolSize���߳�(�̳߳����߳���������maxPoolSize)
        keepAliveTime���Ǻ����̵߳Ŀ���ʱ�䳬��keepAliveTime�ͻᱻ�Զ���ֹ���յ���ע�⵱corePoolSize=maxPoolSizeʱ��keepAliveTime����Ҳ�Ͳ���������(��Ϊ�����ڷǺ����߳�)��
        unit��keepAliveTime��ʱ�䵥λ
        workQueue�����ڱ�������Ķ��У�����Ϊ�޽硢�н硢ͬ���ƽ�new SynchronousQueue<>()���ֶ�������֮һ����������Ĺ����߳�������corePoolSizeʱ����ʱ�½���������ᱻ�ŵ�������
        threadFactory�������̵߳Ĺ����࣬Ĭ��ʹ��Executors.defaultThreadFactory()��Ҳ����ʹ��guava���ThreadFactoryBuilder������
        handler���̳߳��޷�������������(�����������߳����ﵽmaximunPoolSize)ʱ�ı��Ͳ��ԣ�ȡֵ��AbortPolicy��CallerRunsPolicy��DiscardOldestPolicy��DiscardPolicy
        */
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(3, 6, 30, TimeUnit.SECONDS,
                new SynchronousQueue<>());


    }
    @Override
    public void run() {
        System.out.println(i++);
    }

    public static void main(String[] args) {
        ScheduledTask t = new ScheduledTask();
        t.runTask(t);
    }
}

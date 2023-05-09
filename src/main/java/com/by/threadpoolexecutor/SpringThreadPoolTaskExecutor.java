package com.by.threadpoolexecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

public class SpringThreadPoolTaskExecutor {

    public void runTask() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /**���ú����߳���*/
        executor.setCorePoolSize(3);
        /**��������߳���*/
        executor.setMaxPoolSize(6);
        // �Ǻ����̴߳��ʱ��
        executor.setKeepAliveSeconds(30);
        /**���ö��д�С*/
        executor.setQueueCapacity(10);
        /**�ȴ������ڹػ�ʱ���--�����ȴ������߳�ִ����*/
        executor.setWaitForTasksToCompleteOnShutdown(true);
        /** �ȴ�ʱ�� ��Ĭ��Ϊ0����ʱ����ֹͣ������û�ȴ�xx���ǿ��ֹͣ*/
        executor.setAwaitTerminationSeconds(30);
        /**�����̳߳��е��̵߳�����ǰ׺*/
        executor.setThreadNamePrefix("test-async-thread-");
        /**rejection-policy����pool�Ѿ��ﵽmax size��ʱ����δ���������(CALLER_RUNS���������߳���ִ�����񣬶����е��������ڵ��߳���ִ��)*/
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        /**��ʼ��ִ����*/
        executor.initialize();
    }
}

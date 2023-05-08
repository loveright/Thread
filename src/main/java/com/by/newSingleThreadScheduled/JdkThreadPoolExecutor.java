package com.by.newSingleThreadScheduled;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JdkThreadPoolExecutor {



    public void runTask() {
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(3, 6, 30, TimeUnit.SECONDS,
                new SynchronousQueue<>());
    }

    public static void main(String[] args) {

    }
}

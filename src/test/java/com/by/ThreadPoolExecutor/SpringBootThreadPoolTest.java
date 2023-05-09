package com.by.ThreadPoolExecutor;

import com.by.ThreadApplication;
import com.by.springthreadpool.ScheduleTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName SpringBootThreadPoolTest
 * @description: TODO
 * @date 2023/5/9 22:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThreadApplication.class)
public class SpringBootThreadPoolTest {

    @Autowired
    ScheduleTask task;

    @Test
    public void runTask() {
//        task.testAsyn();
        task.testScheduleTask();
    }
}

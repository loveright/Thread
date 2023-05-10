package com.by;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName ThreadApplication
 * @description: TODO
 * @date 2023/5/9 22:02
 */
//@EnableScheduling
@SpringBootApplication
@NacosPropertySource(dataId = "dynamic-thread-pool.yaml",autoRefreshed = true,type = ConfigType.YAML)
public class ThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class, args);
    }
}

package com.by.dynamicthreadpoolbyredis.threadpool;

import com.by.dynamicthreadpool.threadpool.DynamicThreadPoolExecutor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName InitThreadPool
 * @description: TODO
 * @date 2023/5/16 21:57
 */
@Component
public class InitThreadPool implements BeanFactoryPostProcessor {


    private static final Logger log = Logger.getAnonymousLogger();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory
                = (DefaultListableBeanFactory) beanFactory;

        // 注册Bean定义，容器根据定义返回bean
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(DynamicThreadPoolExecutor.class);
        BeanDefinition threadPoolBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

        // 核心线程数
        threadPoolBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(2);
        // 最大线程数
        threadPoolBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(5);
        // 线程名称前缀
        threadPoolBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("thread-");
        defaultListableBeanFactory.registerBeanDefinition("threadPoolExecutor", threadPoolBeanDefinition);

    }
}

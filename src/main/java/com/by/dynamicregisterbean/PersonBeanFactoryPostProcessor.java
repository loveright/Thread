package com.by.dynamicregisterbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * @author zhouboyang
 * @version 1.0
 * @ClassName PersonBeanFactoryPostProcessor
 * @description: 动态注册bean
 * @date 2023/5/14 11:01
 */
public class PersonBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger log = Logger.getAnonymousLogger();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory
                = (DefaultListableBeanFactory) beanFactory;

        // 注册Bean定义，容器根据定义返回bean
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(ThreadPoolTaskExecutor.class);
        BeanDefinition threadPoolBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        defaultListableBeanFactory.registerBeanDefinition("threadPoolExecutor", threadPoolBeanDefinition);

        //注册bean实例
        log.info("register poolExecutor>>>>>>>>>>>>>>>>");
        ThreadPoolTaskExecutor poolExecutor = beanFactory.getBean(ThreadPoolTaskExecutor.class);
        beanFactory.registerSingleton("poolExecutor", poolExecutor);

    }
}

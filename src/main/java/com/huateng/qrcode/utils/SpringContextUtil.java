package com.huateng.qrcode.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 单例模式，可以获取ApplicationContext实例，便于bean注入
 *
 * @author qinyupeng
 * @since 2018-11-16 11:37:59
 */
public class SpringContextUtil implements ApplicationContextAware {

    private ApplicationContext context;

    private SpringContextUtil() {

    }

    private static class HolderClass {
        private static final SpringContextUtil instance = new SpringContextUtil();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public static SpringContextUtil getInstance() {
        return HolderClass.instance;
    }


    public ApplicationContext getApplicationContext() {
        return context;
    }
}

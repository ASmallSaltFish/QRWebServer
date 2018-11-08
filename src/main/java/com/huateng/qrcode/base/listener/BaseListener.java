package com.huateng.qrcode.base.listener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 配置监听，在应用启动和销毁时触发
 */
public abstract class BaseListener implements ServletContextListener {

    private WebApplicationContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        init();
    }

    protected abstract void init();


    public Object getBean(String serviceName) {
        return ctx.getBean(serviceName);
    }
}

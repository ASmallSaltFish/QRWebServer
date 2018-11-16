package com.huateng.qrcode.common.listener;

import com.huateng.qrcode.utils.SpringContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 配置监听，在应用启动和销毁时触发
 */
public abstract class BaseListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        //将applicationContext对象存储在工具类中
        SpringContextUtil.getInstance().setApplicationContext(ctx);
        init();
    }

    protected abstract void init();
}

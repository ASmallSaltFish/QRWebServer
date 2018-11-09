package com.huateng.qrcode.base.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志拦截器（针对于method）
 */
public class LogInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        String argInfo = "args:";
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                argInfo = argInfo + args[i] + ";";
            }
        }
        Object obj;
        try {
            logger.debug(methodInvocation.getMethod().getName() + argInfo);
            long startTime = System.currentTimeMillis();
            obj = methodInvocation.proceed();
            long endTime = System.currentTimeMillis();
            logger.debug(methodInvocation.getMethod().getName() + " use "
                    + (endTime - startTime) + " ms; return: " + obj);
        } catch (Exception e) {
            logger.error(methodInvocation.getMethod().getName() + argInfo
                    + e.getMessage());
            throw e;
        }

        return obj;
    }
}

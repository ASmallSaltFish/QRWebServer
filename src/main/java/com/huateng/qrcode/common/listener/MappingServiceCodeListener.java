package com.huateng.qrcode.common.listener;

import com.huateng.qrcode.qrserver.manager.ServiceConfigMapping;
import com.huateng.qrcode.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContextEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MappingServiceCodeListener extends BaseListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, String> serviceCodeAndClassNameMap = new HashMap<>();

    @Override
    protected void init() {
        logger.info("开始初始化服务码和处理接口类映射。。");
        try {
            //读取service.properties文件，将文件中配置的服务码和处理类路径映射关联关系保存起来
            InputStream inputStream = this.getClass().getResourceAsStream("/service.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            for (String serviceCode : properties.stringPropertyNames()) {
                serviceCodeAndClassNameMap.put(serviceCode, (String) properties.get(serviceCode));
            }

            ApplicationContext applicationContext = SpringContextUtil.getInstance().getApplicationContext();
            ServiceConfigMapping serviceConfigMapping = (ServiceConfigMapping) applicationContext.getBean("serviceConfigMapping");
            serviceConfigMapping.setServiceCodeAndClsNameMap(serviceCodeAndClassNameMap);
        } catch (Exception e) {
            logger.error("读取service.properties配置文件失败，请检查配置！", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("服务码映射监听服务已关闭。。");
    }
}

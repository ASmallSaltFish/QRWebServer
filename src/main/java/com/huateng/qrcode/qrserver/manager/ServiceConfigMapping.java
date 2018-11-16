package com.huateng.qrcode.qrserver.manager;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "serviceConfigMapping")
public class ServiceConfigMapping {

    private Map<String, String> serviceCodeAndClsNameMap;

    public void setServiceCodeAndClsNameMap(Map<String, String> serviceCodeAndClsNameMap) {
        this.serviceCodeAndClsNameMap = serviceCodeAndClsNameMap;
    }

    public Map<String, String> getServiceCodeAndClsNameMap() {
        return serviceCodeAndClsNameMap;
    }


    public ServiceConfigMapping() {

    }

    public ServiceConfigMapping(Map<String, String> serviceCodeAndClsNameMap) {
        this.serviceCodeAndClsNameMap = serviceCodeAndClsNameMap;
    }

    public String getFullClassName(String serviceCode) {
        return serviceCodeAndClsNameMap.get(serviceCode);
    }
}

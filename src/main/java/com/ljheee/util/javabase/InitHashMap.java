package com.ljheee.util.javabase;

import java.util.concurrent.ConcurrentHashMap;


public class InitHashMap {


    // 查询出所有指标
    static ConcurrentHashMap<String, String> metricsExpressionMap = new ConcurrentHashMap<String, String>() {
        {
            metricsExpressionMap.put("M001", "P003+P001");
            metricsExpressionMap.put("M002", "M001+P002*5");
            // ...
        }
    };



    public static void main(String[] args) {
        System.out.println(metricsExpressionMap.values());
    }

}

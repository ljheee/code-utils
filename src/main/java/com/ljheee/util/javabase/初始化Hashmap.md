
双层花括号实现map创建时赋值
https://blog.csdn.net/we_shell/article/details/46344343
但是声明成static的map，会出现静态块初始化失败ExceptionInInitializerError

https://blog.csdn.net/xie_xiansheng/article/details/50831623
https://my.oschina.net/u/1244156/blog/232828


```
static ConcurrentHashMap<String, String> metricsExpressionMap = new ConcurrentHashMap<String, String>() {
    {
        metricsExpressionMap.put("M001", "P003+P001");  //NullPointerException
        metricsExpressionMap.put("M002", "M001+P002*5");
        // ...
    }
};
```
会初始化失败！NullPointerException

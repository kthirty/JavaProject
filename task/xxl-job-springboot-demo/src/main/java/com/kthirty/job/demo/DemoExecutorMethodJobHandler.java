package com.kthirty.job.demo;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

// 注册为bean，被xxl-job发现
@Component
public class DemoExecutorMethodJobHandler {
    @XxlJob(value = "printHelloWorldJobHandler")
    public ReturnT<String> printHelloWorldJobHandler(String param){
        XxlJobLogger.log("DemoExecutorMethodJobHandler Hello World");
        return ReturnT.SUCCESS;
    }
}

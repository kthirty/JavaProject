package com.kthirty.job.demo;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;

public class DemoExecutorClass1JobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("XXL-JOB, DemoExecutorClass1JobHandler 执行."+s);
        return ReturnT.SUCCESS;
    }
}

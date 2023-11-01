package com.ruoyi.web.app.service.impl;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/** 测试服务任务
 * @author wangzhen  2023/10/18 17:51
 */
@Component
public class MyServiceTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("========MyServiceTask start==========");
        System.out.println("getId = " + delegateExecution.getId());
        System.out.println("getProcessInstanceId= " + delegateExecution.getProcessInstanceId());
        System.out.println("========MyServiceTask end==========");
    }
}

package com.ruoyi.web.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author wangzhen  2023/10/25 9:06
 */
@Slf4j
@Component
public class ArchiveReviewTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("打印流程实例ID：processInstanceId{}", delegateExecution.getProcessInstanceId());

        // 会议状态变更
        log.info("在此处更新对应的会议表的会议状态");
    }
}

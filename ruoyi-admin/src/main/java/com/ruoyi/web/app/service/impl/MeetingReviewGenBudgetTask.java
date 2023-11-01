package com.ruoyi.web.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * 生成会议预算 自动节点
 * @author wangzhen  2023/10/23 12:01
 */
@Component
@Slf4j
public class MeetingReviewGenBudgetTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("打印流程实例ID：processInstanceId{}",delegateExecution.getProcessInstanceId());

        // 根据会议预算模板计算会议预算
        // 这里待定是根据模板表里的数据来，还是已经有了计算公式，查询会议表根据金额字段进行计算，
        log.info("在此处计算会议预算并写入对应的表");
    }
}

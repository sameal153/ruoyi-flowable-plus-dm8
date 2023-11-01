package com.ruoyi.web.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author wangzhen  2023/10/23 15:57
 */
@Component
@Slf4j
public class MeetingReviewGenNoticeTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("打印流程实例ID：processInstanceId{}",delegateExecution.getProcessInstanceId());

        // 生成会议通知
        // 这里待定是根据模板表计算还是已经规定好通知消息的格式，比如【您好，您有一条会议通知，请及时查看】等等
        // 然后点超链进入会议消息页面 可以查看会议信息（这个可以单独建一张会议信息表）
        log.info("在此处存储会议通知信息并写入对应的表，然后把消息封装好写入对应的待办信息表");
    }
}

package com.ruoyi.workflow.mapper;

import com.ruoyi.workflow.domain.vo.WfTaskVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangzhen  2023/10/25 17:23
 */
public interface WfRunTaskMapper {
    List<WfTaskVo> selectRunTaskList(@Param(value = "procInsId") String procInsId);
}

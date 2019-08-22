package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains;

import com.rnkrsoft.opensource.iam.enums.JobStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.services.JobService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryJobRecord implements Serializable{
    @ApidocElement(value = "岗位编号", maxLen = 10, unique = true, hidden = true, interfaces = {
            @WebCascadeInterface(displayName = "编辑", serviceClass = JobService.class, value = "toUpdate"),
            @WebCascadeInterface(displayName = "删除", serviceClass = JobService.class, value = "delete", confirm = true)
    })
    Integer jobId;
    @ApidocElement(value = "城市名称", maxLen = 20)
    String cityName;
    @ApidocElement(value = "分支机构", maxLen = 20)
    String branchName;
    @ApidocElement(value = "部门名称", maxLen = 20)
    String departmentName;
    @ApidocElement(value = "岗位名称", maxLen = 36)
    String jobName;
    @ApidocElement(value = "岗位状态", maxLen = 10, enumClass = JobStatus.class, defaults = "1")
    Integer jobStatus;
}

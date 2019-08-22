package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains;

import com.rnkrsoft.opensource.iam.enums.JobStatus;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class UpdateJobRequest implements Serializable {
    @ApidocElement(value = "岗位编号", maxLen = 36, unique = true)
    Integer jobId;
    @ApidocElement(value = "岗位名称", maxLen = 36)
    String jobName;
    @ApidocElement(value = "所属部门", maxLen = 36)
    Integer departmentId;
    @ApidocElement(value = "岗位状态", maxLen = 10, enumClass = JobStatus.class)
    Integer jobStatus;
}

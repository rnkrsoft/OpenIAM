package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains;

import com.rnkrsoft.opensource.iam.enums.JobStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.services.DepartmentService;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class CreateJobRequest implements Serializable{
    @ApidocElement(value = "岗位名称", maxLen = 36, layout = WebLayout.LINEAR)
    String jobName;
    @ApidocElement(value = "所属部门", required = true, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = DepartmentService.class,
                    value = "fetchDepartment=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    Integer departmentId;
    @ApidocElement(value = "岗位状态", maxLen = 10, layout = WebLayout.LINEAR, enumClass = JobStatus.class)
    Integer jobStatus;
}

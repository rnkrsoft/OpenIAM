package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains;

import com.rnkrsoft.opensource.iam.enums.JobStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import lombok.Data;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;
import javax.web.doc.enums.ValueDisplayType;
import javax.web.doc.enums.WebDisplayType;
import javax.web.doc.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class QueryJobRequest extends AbstractRequestPage {
    @ApidocElement(value = "分支机构", required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = BranchService.class,
                    value = "fetchBranch=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    Integer branchId;
    @ApidocElement(value = "岗位状态", maxLen = 10, required = false, enumClass = JobStatus.class)
    Integer jobStatus;
}

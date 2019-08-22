package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.domains;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.doc.enums.ValueDisplayType;
import javax.web.doc.enums.WebDisplayType;
import javax.web.doc.enums.WebLayout;
import javax.web.doc.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class CreateDepartmentRequest implements Serializable {
    @ApidocElement(value = "部门编号", maxLen = 36, unique = true, required = false, layout = WebLayout.LINEAR)
    Integer departmentId;
    @ApidocElement(value = "部门名称", maxLen = 36, layout = WebLayout.LINEAR)
    String departmentName;
    @ApidocElement(value = "分支机构", maxLen = 36, layout = WebLayout.LINEAR, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = BranchService.class,
                    value = "fetchBranch=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    Integer branchId;
}

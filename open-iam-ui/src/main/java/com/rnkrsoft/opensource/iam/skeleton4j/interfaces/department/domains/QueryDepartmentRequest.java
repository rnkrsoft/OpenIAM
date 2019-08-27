package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.domains;

import com.rnkrsoft.opensource.iam.enums.DepartmentStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services.CityService;
import lombok.Data;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class QueryDepartmentRequest extends AbstractRequestPage {
    @ApidocElement(value = "城市", maxLen = 10, required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = CityService.class,
                    value = "fetchCity=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    Integer cityId;

    @ApidocElement(value = "分支机构", maxLen = 36, required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = BranchService.class,
                    value = "fetchBranch=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    Integer branchId;
    @ApidocElement(value = "状态", maxLen = 10, required = true, enumClass = DepartmentStatus.class, defaults = "1")
    Integer departmentStatus;
}

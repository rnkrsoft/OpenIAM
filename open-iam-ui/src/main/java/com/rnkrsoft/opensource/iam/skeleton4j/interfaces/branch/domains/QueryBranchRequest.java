package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains;

import com.rnkrsoft.opensource.iam.enums.BranchStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services.CityService;
import lombok.Data;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;
import javax.web.doc.enums.ValueDisplayType;
import javax.web.doc.enums.WebDisplayType;
import javax.web.doc.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class QueryBranchRequest extends AbstractRequestPage{
    @ApidocElement(value = "所属城市", maxLen = 10, required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = CityService.class,
                    value = "fetchCity=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    Integer cityId;

    @ApidocElement(value = "机构状态", maxLen = 10, enumClass = BranchStatus.class, defaults = "1")
    Integer branchStatus;
}

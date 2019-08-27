package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services.CityService;
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
public class CreateBranchRequest implements Serializable{
    @ApidocElement(value = "机构编号", maxLen = 10, unique = true, required = false, layout = WebLayout.LINEAR)
    Integer branchId;
    @ApidocElement(value = "机构名称", maxLen = 36, layout = WebLayout.LINEAR)
    String branchName;
    @ApidocElement(value = "所属城市", maxLen = 10, layout = WebLayout.LINEAR, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = CityService.class,
                    value = "fetchCity=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    Integer cityId;
}

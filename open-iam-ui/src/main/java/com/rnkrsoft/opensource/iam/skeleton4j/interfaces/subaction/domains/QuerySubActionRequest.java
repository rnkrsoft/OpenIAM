package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains;

import com.rnkrsoft.opensource.iam.enums.SubActionStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.services.ActionService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.services.ProductService;
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
public class QuerySubActionRequest extends AbstractRequestPage{
    @ApidocElement(value = "应用名称", maxLen = 20, required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = ApplicationService.class,
                    value = "fetchApp=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    String applicationId;

    @ApidocElement(value = "产品名称", maxLen = 20, required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = ProductService.class,
                    value = "fetchProduct=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    String productId;

    @ApidocElement(value = "功能名称", maxLen = 20, required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = ActionService.class,
                    value = "fetchAction=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    String actionId;

    @ApidocElement(value = "操作代码", required = false, maxLen = 36)
    String subActionCode;

    @ApidocElement(value = "操作状态", required = false, enumClass = SubActionStatus.class)
    Integer subActionStatus;
}

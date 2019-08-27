package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains;

import com.rnkrsoft.opensource.iam.enums.ActionStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.services.ActionService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.services.ProductService;
import lombok.Data;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebTriggerEvent;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class QueryActionRequest extends AbstractRequestPage{
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

    @ApidocElement(value = "功能代码", maxLen = 20, required = false)
    String actionCode;

    @ApidocElement(value = "功能名称", maxLen = 20, required = false, placeholder = "请输入功能名称，支持模糊搜索")
    String actionTitle;

    @ApidocElement(value = "功能状态", maxLen = 20, required = false, enumClass = ActionStatus.class, defaults = "1")
    Integer actionStatus;
}

package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains;

import com.rnkrsoft.opensource.iam.enums.ProductStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.services.ProductService;
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
public class QueryProductRequest extends AbstractRequestPage {
    @ApidocElement(value = "应用名称", maxLen = 20, required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = ApplicationService.class,
                    value = "fetchApp=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    String applicationId;

    @ApidocElement(value = "产品代码", maxLen = 36, required = false)
    String productCode;

    @ApidocElement(value = "产品名称", maxLen = 36, required = false, placeholder = "请输入产品名称，支持模糊搜索")
    String productTitle;

    @ApidocElement(value = "产品状态", maxLen = 10, required = false, enumClass = ProductStatus.class)
    Integer productStatus;
}

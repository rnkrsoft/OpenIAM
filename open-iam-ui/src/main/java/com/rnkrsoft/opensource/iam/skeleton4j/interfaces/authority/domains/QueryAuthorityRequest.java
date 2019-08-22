package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.domains;

import com.rnkrsoft.opensource.iam.enums.ResourceType;
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
public class QueryAuthorityRequest extends AbstractRequestPage {
    @ApidocElement(value = "权限类型", maxLen = 36, enumClass = ResourceType.class, defaults = "1")
    Integer resourceType;
    @ApidocElement(value = "资源名称", maxLen = 40, required = false)
    String resourceCode;
    @ApidocElement(value = "资源名称", maxLen = 40, required = false)
    String resourceName;

}

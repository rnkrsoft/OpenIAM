package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains;

import com.rnkrsoft.opensource.iam.enums.ApplicationStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class ToUpdateApplicationResponse extends AbstractResponse{
    @ApidocElement(value = "应用编号", unique = true, maxLen = 10, layout = WebLayout.LINEAR, interfaces = {
            @WebCascadeInterface(displayName = "保存", serviceClass = ApplicationService.class, value = "update")
    })
    Integer appId;
    @ApidocElement(value = "应用代码", maxLen = 36, layout = WebLayout.LINEAR)
    String appCode;
    @ApidocElement(value = "应用名称", maxLen = 40, layout = WebLayout.LINEAR)
    String appTitle;
    @ApidocElement(value = "应用图标", maxLen = 255, required = false, layout = WebLayout.LINEAR, valueDisplayType = ValueDisplayType.IMAGE)
    String appIcon;
    @ApidocElement(value = "应用地址", maxLen = 255, layout = WebLayout.LINEAR)
    String appUrl;
    @ApidocElement(value = "状态", maxLen = 20, layout = WebLayout.LINEAR, enumClass = ApplicationStatus.class)
    Integer appStatus;
}

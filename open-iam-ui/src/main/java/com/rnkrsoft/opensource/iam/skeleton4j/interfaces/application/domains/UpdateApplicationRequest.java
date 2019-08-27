package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains;

import com.rnkrsoft.opensource.iam.enums.ApplicationStatus;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class UpdateApplicationRequest implements Serializable{
    @ApidocElement(value = "应用编号", unique = true, maxLen = 10)
    Integer appId;
    @ApidocElement(value = "应用代码", maxLen = 36)
    String appCode;
    @ApidocElement(value = "应用名称", maxLen = 40)
    String appTitle;
    @ApidocElement(value = "应用图标", maxLen = 255, required = false, valueDisplayType = ValueDisplayType.IMAGE)
    String appIcon;
    @ApidocElement(value = "应用地址", maxLen = 255, layout = WebLayout.LINEAR)
    String appUrl;
    @ApidocElement(value = "状态", maxLen = 20, enumClass = ApplicationStatus.class)
    Integer appStatus;
}

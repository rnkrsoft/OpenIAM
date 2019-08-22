package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.doc.enums.ValueDisplayType;
import javax.web.doc.enums.WebLayout;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class CreateApplicationRequest implements Serializable{
    @ApidocElement(value = "应用代码", maxLen = 36, layout = WebLayout.LINEAR)
    String appCode;
    @ApidocElement(value = "应用名称", maxLen = 40, layout = WebLayout.LINEAR)
    String appTitle;
    @ApidocElement(value = "应用地址", maxLen = 255, layout = WebLayout.LINEAR)
    String appUrl;
    @ApidocElement(value = "应用图标", maxLen = 255, layout = WebLayout.LINEAR, required = false, valueDisplayType = ValueDisplayType.IMAGE)
    String appIcon;
}

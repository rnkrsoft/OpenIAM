package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains;

import com.rnkrsoft.opensource.iam.enums.ApplicationStatus;
import lombok.Data;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class QueryApplicationRequest extends AbstractRequestPage{
    @ApidocElement(value = "应用代码", maxLen = 36, required = false, placeholder = "请输入应用代码")
    String appCode;
    @ApidocElement(value = "应用名称", maxLen = 40, required = false, placeholder = "支持模糊查找")
    String appTitle;
    @ApidocElement(value = "状态", maxLen = 20, enumClass = ApplicationStatus.class, defaults = "1")
    Integer appStatus;
}

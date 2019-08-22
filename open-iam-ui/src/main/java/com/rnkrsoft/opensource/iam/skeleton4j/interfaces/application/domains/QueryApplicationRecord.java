package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains;

import com.rnkrsoft.opensource.iam.enums.ApplicationStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryApplicationRecord implements Serializable{
    @ApidocElement(value = "应用编号", unique = true, hidden = true, maxLen = 10, interfaces = {
            @WebCascadeInterface(displayName = "编辑", serviceClass = ApplicationService.class, value = "toUpdate"),
            @WebCascadeInterface(displayName = "修复权限", serviceClass = ApplicationService.class, value = "fix", confirm = true)
    })
    Integer appId;
    @ApidocElement(value = "应用代码", maxLen = 20)
    String appCode;
    @ApidocElement(value = "应用名称", maxLen = 20)
    String appTitle;
    @ApidocElement(value = "应用地址", maxLen = 60)
    String appUrl;
    @ApidocElement(value = "状态", maxLen = 10, enumClass = ApplicationStatus.class)
    Integer appStatus;
}

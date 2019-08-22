package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains;

import com.rnkrsoft.opensource.iam.enums.SubActionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuerySubActionRecord implements Serializable{
    @ApidocElement(value = "操作编号", maxLen = 10, unique = true, hidden = true)
    Integer subActionId;

    @ApidocElement(value = "操作代码", maxLen = 15)
    String subActionCode;

    @ApidocElement(value = "应用名称", maxLen = 20)
    String applicationTitle;

    @ApidocElement(value = "产品名称", maxLen = 20)
    String productTitle;

    @ApidocElement(value = "功能名称", maxLen = 20)
    String actionTitle;

    @ApidocElement(value = "操作名称", maxLen = 20)
    String subActionTitle;

    @ApidocElement(value = "操作状态", maxLen = 10, enumClass = SubActionStatus.class)
    Integer subActionStatus;
}

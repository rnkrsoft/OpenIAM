package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains;

import com.rnkrsoft.opensource.iam.enums.ActionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryActionRecord implements Serializable {
    @ApidocElement(value = "功能编号", maxLen = 20, unique = true, hidden = true)
    Integer actionId;
    @ApidocElement(value = "功能代码", maxLen = 20)
    String actionCode;
    @ApidocElement(value = "应用名称", maxLen = 20)
    String applicationTitle;
    @ApidocElement(value = "产品名称", maxLen = 20)
    String productTitle;
    @ApidocElement(value = "功能名称", maxLen = 20)
    String actionTitle;
    @ApidocElement(value = "状态", maxLen = 20, valueDisplayType = ValueDisplayType.SELECTION, enumClass = ActionStatus.class)
    Integer actionState;
}

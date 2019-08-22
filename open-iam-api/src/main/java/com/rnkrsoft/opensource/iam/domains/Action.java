package com.rnkrsoft.opensource.iam.domains;

import lombok.*;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Action implements Serializable {
    @ApidocElement("功能编号")
    String actionCode;
    @ApidocElement("功能编号")
    String actionName;
    @ApidocElement("操作id")
    Integer actionId;
    @ApidocElement("操作列表")
    final Set<SubAction> subActions = new HashSet();
}

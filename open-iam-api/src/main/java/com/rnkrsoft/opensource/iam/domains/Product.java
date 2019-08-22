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
public class Product implements Serializable {
    @ApidocElement("项目编号")
    String productCode;
    @ApidocElement("项目名称")
    String productName;
    @ApidocElement("操作id")
    Integer productId;
    @ApidocElement("功能列表")
    final Set<Action> actions = new HashSet();
}

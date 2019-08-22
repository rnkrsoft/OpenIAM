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
public class Application implements Serializable {
    @ApidocElement("系统编号")
    String appCode;
    @ApidocElement("系统名称")
    String appName;
    @ApidocElement("操作id")
    Integer appId;
    @ApidocElement("操作列表")
    final Set<Product> products = new HashSet();
}
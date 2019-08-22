package com.rnkrsoft.opensource.iam.domains;

import lombok.*;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/22.
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityRecord implements Serializable{
    @ApidocElement("路径")
    String path;
    @ApidocElement("系统编号")
    String appCode;
    @ApidocElement("系统名称")
    String appName;
    @ApidocElement("产品编号")
    String productCode;
    @ApidocElement("产品名称")
    String productName;
    @ApidocElement("功能编号")
    String actionCode;
    @ApidocElement("功能名称")
    String actionName;
    @ApidocElement("操作编号")
    String subActionCode;
    @ApidocElement("操作名称")
    String subActionName;
}

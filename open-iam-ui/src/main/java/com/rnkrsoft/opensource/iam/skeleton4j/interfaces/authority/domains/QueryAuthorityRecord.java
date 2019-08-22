package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.domains;

import com.rnkrsoft.opensource.iam.enums.ResourceType;
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
public class QueryAuthorityRecord implements Serializable {
    @ApidocElement(value = "权限编号", maxLen = 15, unique = true, hidden = true)
    Integer authorityId;
    @ApidocElement(value = "权限类型", maxLen = 10, enumClass = ResourceType.class)
    Integer resourceType;
    @ApidocElement(value = "资源编号", maxLen = 20)
    Integer resourceId;
    @ApidocElement(value = "资源代码", maxLen = 50)
    String resourceCode;
    @ApidocElement(value = "资源名称", maxLen = 50)
    String resourceName;
}

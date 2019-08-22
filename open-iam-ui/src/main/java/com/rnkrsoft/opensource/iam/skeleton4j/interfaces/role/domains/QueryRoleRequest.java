package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains;

import com.rnkrsoft.opensource.iam.enums.RoleStatus;
import lombok.Data;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class QueryRoleRequest extends AbstractRequestPage{
    @ApidocElement(value = "角色名称", maxLen = 36, required = false, placeholder = "请输入角色名称，支持模糊查询")
    String roleName;
    @ApidocElement(value = "角色状态", maxLen = 10, enumClass = RoleStatus.class, defaults = "1")
    Integer roleStatus;
}

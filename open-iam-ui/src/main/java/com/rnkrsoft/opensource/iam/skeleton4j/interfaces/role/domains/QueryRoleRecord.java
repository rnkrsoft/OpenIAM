package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains;

import com.rnkrsoft.opensource.iam.enums.RoleStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services.RoleService;
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
public class QueryRoleRecord implements Serializable{
    @ApidocElement(value = "角色编号", maxLen = 36, unique = true, hidden = true, interfaces = {
            @WebCascadeInterface(displayName = "删除", serviceClass = RoleService.class, value = "delete", confirm = true),
            @WebCascadeInterface(displayName = "编辑", serviceClass = RoleService.class, value = "toUpdate")
    })
    Integer roleId;
    @ApidocElement(value = "角色名称", maxLen = 36)
    String roleName;
    @ApidocElement(value = "角色状态", maxLen = 10, enumClass = RoleStatus.class)
    Integer roleStatus;
}

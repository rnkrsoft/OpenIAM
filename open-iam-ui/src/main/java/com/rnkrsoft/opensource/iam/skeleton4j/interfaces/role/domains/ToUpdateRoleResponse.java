package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains;

import com.rnkrsoft.opensource.iam.enums.RoleStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services.RoleService;
import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import javax.web.doc.enums.WebLayout;
import javax.web.skeleton4j.annotation.WebCascadeInterface;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class ToUpdateRoleResponse extends AbstractResponse {
    @ApidocElement(value = "角色编号", unique = true, hidden = true, layout = WebLayout.LINEAR,interfaces = {
            @WebCascadeInterface(displayName = "保存", serviceClass = RoleService.class, value = "update")
    })
    Integer roleId;
    @ApidocElement(value = "角色名称", maxLen = 36, layout = WebLayout.LINEAR)
    String roleName;
    @ApidocElement(value = "角色状态", maxLen = 10, layout = WebLayout.LINEAR, enumClass = RoleStatus.class)
    Integer roleStatus;
}

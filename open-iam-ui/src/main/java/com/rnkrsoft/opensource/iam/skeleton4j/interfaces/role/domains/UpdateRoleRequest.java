package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains;


import com.rnkrsoft.opensource.iam.enums.RoleStatus;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class UpdateRoleRequest implements Serializable{
    @ApidocElement(value = "角色编号", unique = true)
    Integer roleId;
    @ApidocElement(value = "角色名称", maxLen = 36)
    String roleName;
    @ApidocElement(value = "角色状态", maxLen = 10, enumClass = RoleStatus.class)
    Integer roleStatus;
}

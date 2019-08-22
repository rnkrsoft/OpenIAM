package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class CreateRoleRequest implements Serializable{
    @ApidocElement(value = "角色名称", maxLen = 36)
    String roleName;
}

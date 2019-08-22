package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class FetchAuthorityByRoleRequest implements Serializable{
    @ApidocElement("角色编号")
    Integer roleId;
    @ApidocElement("应用代码")
    Integer appId;
}

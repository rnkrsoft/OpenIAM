package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class BatchUpdateAuthorityByRoleRequest implements Serializable{
    @ApidocElement("角色编号")
    Integer roleId;
    @ApidocElement("应用代码")
    String appId;
    @ApidocElement(value = "数据，为Node的JSON数据", maxLen = Integer.MAX_VALUE)
    String data;
    @Data
    public static class Node {
        Integer subActionId;
    }
}

package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("角色服务")
public interface RoleService {
    @ApidocInterface("创建")
    CreateRoleResponse create(CreateRoleRequest request);
    @ApidocInterface("删除")
    DeleteRoleResponse delete(DeleteRoleRequest request);
    @ApidocInterface("编辑查看")
    ToUpdateRoleResponse toUpdate(ToUpdateRoleRequest request);
    @ApidocInterface("编辑")
    UpdateRoleResponse update(UpdateRoleRequest request);
    @ApidocInterface("查询")
    QueryRoleResponse query(QueryRoleRequest request);
    @ApidocInterface("获取角色列表")
    FetchRoleResponse fetch(FetchRoleRequest request);

}

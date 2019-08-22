package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains.*;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
public class RoleServiceImpl implements RoleService{
    @Override
    public CreateRoleResponse create(CreateRoleRequest request) {
        CreateRoleResponse response = new CreateRoleResponse();
        return response;
    }

    @Override
    public DeleteRoleResponse delete(DeleteRoleRequest request) {
        DeleteRoleResponse response = new DeleteRoleResponse();
        return response;
    }

    @Override
    public ToUpdateRoleResponse toUpdate(ToUpdateRoleRequest request) {
        ToUpdateRoleResponse response = new ToUpdateRoleResponse();
        response.setRoleId(request.getRoleId());
        response.setRoleName("角色" + request.getRoleId());
        return response;
    }

    @Override
    public UpdateRoleResponse update(UpdateRoleRequest request) {
        UpdateRoleResponse response = new UpdateRoleResponse();
        return response;
    }

    @Override
    public QueryRoleResponse query(QueryRoleRequest request) {
        QueryRoleResponse response = new QueryRoleResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryRoleRecord.builder().roleId(i).roleName("测试角色"+ i).build());
        }
        return response;
    }

    @Override
    public FetchRoleResponse fetch(FetchRoleRequest request) {
        FetchRoleResponse response = new FetchRoleResponse();
        response.addNode(Node.builder().text("测试角色1").value(Integer.toString(1)).build());
        response.addNode(Node.builder().text("测试角色2").value(Integer.toString(2)).build());
        return response;
    }
}

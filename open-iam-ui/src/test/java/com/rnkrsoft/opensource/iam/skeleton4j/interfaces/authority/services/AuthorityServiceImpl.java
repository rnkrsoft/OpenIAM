package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.services;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rnkrsoft.opensource.iam.enums.ResourceType;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.domains.*;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
public class AuthorityServiceImpl implements AuthorityService {

    @Override
    public FetchAuthorityByRoleResponse fetchAuthorityByRole(FetchAuthorityByRoleRequest request) {
        FetchAuthorityByRoleResponse response = new FetchAuthorityByRoleResponse();
        Node applicationNode = Node.builder().text("测试系统").value("1").selected(true).build();
        Node productNode = Node.builder().text("测试产品").value("11").selected(true).build();
        Node actionNode = Node.builder().text("产品种类管理").value("111").selected(true).build();
        {
            Node subActionNode = Node.builder().text("页面").value("1111").selected(true).build();
            actionNode.addNode(subActionNode);
        }
        {
            Node subActionNode = Node.builder().text("添加").value("1112").selected(true).build();
            actionNode.addNode(subActionNode);
        }
        {
            Node subActionNode = Node.builder().text("删除").value("1113").selected(false).build();
            actionNode.addNode(subActionNode);
        }
        productNode.addNode(actionNode);
        applicationNode.addNode(productNode);
        response.addNode(applicationNode);
        return response;
    }

    @Override
    public QueryAuthorityResponse query(QueryAuthorityRequest request) {
        QueryAuthorityResponse response = new QueryAuthorityResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryAuthorityRecord.builder()
                    .authorityId(i)
                    .resourceType(ResourceType.SKELETON4J.getCode())
                    .resourceCode("sys" + i)
                    .resourceName("应用系统" + i)
                    .build());
        }
        return response;
    }

    @Override
    public BatchUpdateAuthorityByRoleResponse batchUpdateByRole(BatchUpdateAuthorityByRoleRequest request) {
        BatchUpdateAuthorityByRoleResponse response = new BatchUpdateAuthorityByRoleResponse();
        if (request.getRoleId() == null) {
            response.setRspCode(IAMResponseCode.UNSELECT_ROLE);
            return response;
        }
        Type type = new TypeToken<List<BatchUpdateAuthorityByRoleRequest.Node>>() {}.getType();
        List<BatchUpdateAuthorityByRoleRequest.Node> authorities = new GsonBuilder().create().fromJson(request.getData(), type);
        //TODO 先删除所有的关于该角色的权限
        //TODO 批量建立操作与角色的关系
        return response;
    }
}

package com.rnkrsoft.opensource.iam.services;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.cache.dao.AuthorityCache;
import com.rnkrsoft.opensource.iam.enums.ResourceType;
import com.rnkrsoft.opensource.iam.jdbc.bo.FetchAuthorityByAppBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryAuthorityBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.AuthorityDAO;
import com.rnkrsoft.opensource.iam.jdbc.dao.RoleAuthorityDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.RoleAuthorityEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.services.AuthorityService;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired
    RoleAuthorityDAO roleAuthorityDAO;
    @Autowired
    AuthorityCacheHelperService authorityCacheHelperService;

    @Override
    public FetchAuthorityByRoleResponse fetchAuthorityByRole(FetchAuthorityByRoleRequest request) {
        FetchAuthorityByRoleResponse response = new FetchAuthorityByRoleResponse();
        List<FetchAuthorityByAppBO> fetchAuthorityByAppBOs = authorityDAO.fetchAuthorityByApp(request.getAppId());
        Set<Integer> authorities = new HashSet(authorityDAO.selectSubActionsByRole(request.getRoleId()));
        Node applicationNode = null;
        Node productNode = null;
        Node actionNode = null;
        for (FetchAuthorityByAppBO record : fetchAuthorityByAppBOs) {
            if (applicationNode == null || !applicationNode.getValue().equals(Integer.toString(record.getAppId()))) {
                applicationNode = Node.builder()
                        .text(record.getAppTitle())
                        .value(Integer.toString(record.getAppId()))
                        .build();
                response.addNode(applicationNode);
            }
            if (productNode == null || !productNode.getValue().equals(Integer.toString(record.getProductId()))) {
                productNode = Node.builder()
                        .text(record.getProductTitle())
                        .value(Integer.toString(record.getProductId()))
                        .build();
                applicationNode.addNode(productNode);
            }
            if (actionNode == null || !actionNode.getValue().equals(Integer.toString(record.getActionId()))) {
                actionNode = Node.builder()
                        .text(record.getActionTitle())
                        .value(Integer.toString(record.getActionId()))
                        .build();
                productNode.addNode(actionNode);
            }
            Node subActionNode = Node.builder()
                    .text(record.getSubActionTitle())
                    .value(Integer.toString(record.getSubActionId()))
                    .selected(authorities.contains(record.getSubActionId()))
                    .build();
            if (!applicationNode.isSelected() && authorities.contains(record.getSubActionId())){
                applicationNode.setSelected(true);
            }
            if (!productNode.isSelected() && authorities.contains(record.getSubActionId())){
                productNode.setSelected(true);
            }
            if (!actionNode.isSelected() && authorities.contains(record.getSubActionId())){
                actionNode.setSelected(true);
            }
            actionNode.addNode(subActionNode);
        }
        return response;
    }

    @Override
    public QueryAuthorityResponse query(QueryAuthorityRequest request) {
        QueryAuthorityResponse response = new QueryAuthorityResponse();
        Pagination<QueryAuthorityBO> pagination = new Pagination<QueryAuthorityBO>(request.getPageSize(), request.getPageNo());
        if (StringUtils.isNotBlank(request.getResourceType())){
            pagination.getParams().put("resourceType", request.getResourceType());
        }
        if (StringUtils.isNotBlank(request.getResourceCode())){
            pagination.getParams().put("resourceCode", request.getResourceCode());
        }
        if (StringUtils.isNotBlank(request.getResourceName())){
            pagination.getParams().put("resourceName", request.getResourceName());
        }
        authorityDAO.queryAuthority(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QueryAuthorityBO record : pagination.getRecords()) {
            response.addRecord(QueryAuthorityRecord.builder()
                    .authorityId(record.getAuthorityId())
                    .resourceType(record.getResourceType())
                    .resourceId(record.getResourceId())
                    .resourceCode(record.getResourceCode())
                    .resourceName(record.getResourceName())
                    .build());
        }
        return response;
    }

    @Override
    public BatchUpdateAuthorityByRoleResponse batchUpdateByRole(BatchUpdateAuthorityByRoleRequest request) {
        BatchUpdateAuthorityByRoleResponse response = new BatchUpdateAuthorityByRoleResponse();
        authorityCacheHelperService.clear("*");
        Type type = new TypeToken<List<BatchUpdateAuthorityByRoleRequest.Node>>() {
        }.getType();
        //先删除所有的关于该角色的权限
        roleAuthorityDAO.deleteAnd(RoleAuthorityEntity.builder().roleId(request.getRoleId()).build());
        List<BatchUpdateAuthorityByRoleRequest.Node> authorities = new GsonBuilder().create().fromJson(request.getData(), type);
        //批量建立操作与角色的关系
        Set<Integer> subActions = new HashSet();
        for (BatchUpdateAuthorityByRoleRequest.Node record : authorities) {
            subActions.add(record.getSubActionId());
        }
        if (!subActions.isEmpty()) {
            roleAuthorityDAO.insertAuthorities(request.getRoleId(), subActions);
        }
        return response;
    }
}

package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.cache.dao.AuthorityCache;
import com.rnkrsoft.opensource.iam.enums.RoleStatus;
import com.rnkrsoft.opensource.iam.jdbc.dao.RoleDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.RoleEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services.RoleService;
import com.rnkrsoft.opensource.iam.vo.Role;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;
import java.util.List;
import java.util.Set;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    AuthorityCacheHelperService authorityCacheHelperService;
    @Override
    public CreateRoleResponse create(CreateRoleRequest request) {
        CreateRoleResponse response = new CreateRoleResponse();
        roleDAO.insertSelective(RoleEntity.builder().roleName(request.getRoleName()).roleStatus(RoleStatus.ENABLED.getCode()).build());
        return response;
    }

    @Override
    public DeleteRoleResponse delete(DeleteRoleRequest request) {
        DeleteRoleResponse response = new DeleteRoleResponse();
        roleDAO.deleteByPrimaryKey(request.getRoleId());
        authorityCacheHelperService.clear("*");
        return response;
    }

    @Override
    public ToUpdateRoleResponse toUpdate(ToUpdateRoleRequest request) {
        ToUpdateRoleResponse response = new ToUpdateRoleResponse();
        RoleEntity roleEntity = roleDAO.selectByPrimaryKey(request.getRoleId());
        if (roleEntity == null){
            return response;
        }
        response.setRoleId(request.getRoleId());
        response.setRoleName(roleEntity.getRoleName());
        response.setRoleStatus(roleEntity.getRoleStatus());
        return response;
    }

    @Override
    public UpdateRoleResponse update(UpdateRoleRequest request) {
        UpdateRoleResponse response = new UpdateRoleResponse();
        RoleEntity roleEntity = roleDAO.selectByPrimaryKey(request.getRoleId());
        if (roleEntity == null){
            return response;
        }
        roleDAO.updateByPrimaryKeySelective(RoleEntity.builder()
                        .roleId(request.getRoleId())
                        .roleName(request.getRoleName())
                        .roleStatus(request.getRoleStatus())
                        .build()
        );
        authorityCacheHelperService.clear("*");
        return response;
    }

    @Override
    public QueryRoleResponse query(QueryRoleRequest request) {
        QueryRoleResponse response = new QueryRoleResponse();
        RoleEntity roleEntity = new RoleEntity();
        if (StringUtils.isNotBlank(request.getRoleName())){
            roleEntity.setRoleName(request.getRoleName());
        }
        if (StringUtils.isNotBlank(request.getRoleStatus())){
            roleEntity.setRoleStatus(request.getRoleStatus());
        }
        Pagination<RoleEntity> pagination = new Pagination<RoleEntity>(request.getPageSize(), request.getPageNo(), roleEntity);
        roleDAO.selectPageAnd(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (RoleEntity record : pagination.getRecords()) {
            response.addRecord(QueryRoleRecord.builder()
                            .roleId(record.getRoleId())
                            .roleName(record.getRoleName())
                            .roleStatus(record.getRoleStatus())
                            .build()
            );
        }
        return response;
    }

    @Override
    public FetchRoleResponse fetch(FetchRoleRequest request) {
        FetchRoleResponse response = new FetchRoleResponse();
        List<RoleEntity> roleEntities = roleDAO.selectAnd(RoleEntity.builder().roleStatus(RoleStatus.ENABLED.getCode()).build());
        for (RoleEntity record : roleEntities){
            response.addNode(Node.builder().text(record.getRoleName()).value(Integer.toString(record.getRoleId())).build());
        }
        return response;
    }
}

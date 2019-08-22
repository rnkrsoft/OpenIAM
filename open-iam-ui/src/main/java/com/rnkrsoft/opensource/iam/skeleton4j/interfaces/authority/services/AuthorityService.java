package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("权限服务")
public interface AuthorityService {
    @ApidocInterface("按照角色获取权限")
    FetchAuthorityByRoleResponse fetchAuthorityByRole(FetchAuthorityByRoleRequest request);
    @ApidocInterface("查询权限")
    QueryAuthorityResponse query(QueryAuthorityRequest request);
    @ApidocInterface("批量修改权限")
    BatchUpdateAuthorityByRoleResponse batchUpdateByRole(BatchUpdateAuthorityByRoleRequest request);
}

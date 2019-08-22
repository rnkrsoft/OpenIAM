package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("用户服务")
public interface UserService {
    @ApidocInterface("查询")
    QueryUserResponse query(QueryUserRequest request);
    @ApidocInterface("创建")
    CreateUserResponse create(CreateUserRequest request);
    @ApidocInterface("查看")
    ViewUserResponse view(ViewUserRequest request);
    @ApidocInterface("编辑查看")
    ToUpdateUserResponse toUpdate(ToUpdateUserRequest request);
    @ApidocInterface("编辑")
    UpdateUserResponse update(UpdateUserRequest request);
    @ApidocInterface("启用用户")
    EnableUserResponse enable(EnableUserRequest request);
    @ApidocInterface("禁用用户")
    DisableUserResponse disable(DisableUserRequest request);
    @ApidocInterface("逻辑删除用户")
    LogicDeleteUserResponse logicDelete(LogicDeleteUserRequest request);
}

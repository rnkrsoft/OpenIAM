package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("应用服务")
public interface ApplicationService {
    @ApidocInterface("创建应用")
    CreateApplicationResponse create(CreateApplicationRequest request);
    @ApidocInterface("编辑查看")
    ToUpdateApplicationResponse toUpdate(ToUpdateApplicationRequest request);
    @ApidocInterface("编辑")
    UpdateApplicationResponse update(UpdateApplicationRequest request);
    @ApidocInterface("查询")
    QueryApplicationResponse query(QueryApplicationRequest request);
    @ApidocInterface("获取应用列表")
    FetchApplicationResponse fetch(FetchApplicationRequest request);
    @ApidocInterface("修复权限")
    FixApplicationAuthorityResponse fix(FixApplicationAuthorityRequest request);
}

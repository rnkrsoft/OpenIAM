package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("部门服务")
public interface DepartmentService {
    @ApidocInterface("创建")
    CreateDepartmentResponse create(CreateDepartmentRequest request);
    @ApidocInterface("删除")
    DeleteDepartmentResponse delete(DeleteDepartmentRequest request);
    @ApidocInterface("编辑查看")
    ToUpdateDepartmentResponse toUpdate(ToUpdateDepartmentRequest request);
    @ApidocInterface("编辑")
    UpdateDepartmentResponse update(UpdateDepartmentRequest request);
    @ApidocInterface("查询")
    QueryDepartmentResponse query(QueryDepartmentRequest request);
    @ApidocInterface("拉取")
    FetchDepartmentResponse fetch(FetchDepartmentRequest request);
}

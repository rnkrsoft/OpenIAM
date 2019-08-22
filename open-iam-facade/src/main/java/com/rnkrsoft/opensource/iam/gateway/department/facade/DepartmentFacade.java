package com.rnkrsoft.opensource.iam.gateway.department.facade;

import com.rnkrsoft.opensource.iam.gateway.department.domains.SearchDepartmentRequest;
import com.rnkrsoft.opensource.iam.gateway.department.domains.SearchDepartmentResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by woate on 2019/7/14.
 */
@ApidocService(value = "部门服务", channel = "iam")
public interface DepartmentFacade {
    @ApidocInterface(value = "搜索部门", name = "501", version = "1")
    SearchDepartmentResponse searchDepartment(SearchDepartmentRequest request);
}

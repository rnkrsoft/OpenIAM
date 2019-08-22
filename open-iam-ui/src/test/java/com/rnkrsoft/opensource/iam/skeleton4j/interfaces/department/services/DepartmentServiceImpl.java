package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.services;

import com.rnkrsoft.opensource.iam.enums.DepartmentStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains.QueryJobRecord;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains.QueryJobResponse;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
public class DepartmentServiceImpl implements DepartmentService{
    @Override
    public CreateDepartmentResponse create(CreateDepartmentRequest request) {
        CreateDepartmentResponse response = new CreateDepartmentResponse();
        return response;
    }

    @Override
    public DeleteDepartmentResponse delete(DeleteDepartmentRequest request) {
        DeleteDepartmentResponse response = new DeleteDepartmentResponse();
        return response;
    }

    @Override
    public ToUpdateDepartmentResponse toUpdate(ToUpdateDepartmentRequest request) {
        ToUpdateDepartmentResponse response = new ToUpdateDepartmentResponse();
        response.setDepartmentId(request.getDepartmentId());
        response.setDepartmentName("信息中心");
        response.setBranchId(1);
        return response;
    }

    @Override
    public UpdateDepartmentResponse update(UpdateDepartmentRequest request) {
        UpdateDepartmentResponse response = new UpdateDepartmentResponse();
        return response;
    }

    @Override
    public QueryDepartmentResponse query(QueryDepartmentRequest request) {
        QueryDepartmentResponse response = new QueryDepartmentResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryDepartmentRecord.builder()
                    .departmentId(i)
                    .departmentName("信息中心" + i)
                    .branchName("分支机构")
                    .cityName("重庆" + i)
                    .departmentStatus(DepartmentStatus.ENABLED.getCode())
                    .build());
        }
        return response;
    }

    @Override
    public FetchDepartmentResponse fetch(FetchDepartmentRequest request) {
        FetchDepartmentResponse response = new FetchDepartmentResponse();
        response.addNode(Node.builder().text("信息部1").value("1").build());
        response.addNode(Node.builder().text("信息部2").value("2").build());
        return response;
    }
}

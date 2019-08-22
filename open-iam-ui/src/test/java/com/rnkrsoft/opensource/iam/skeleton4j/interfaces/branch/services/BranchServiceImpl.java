package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services;

import com.rnkrsoft.opensource.iam.enums.BranchStatus;
import com.rnkrsoft.opensource.iam.enums.DepartmentStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains.*;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
public class BranchServiceImpl implements BranchService{
    @Override
    public CreateBranchResponse create(CreateBranchRequest request) {
        CreateBranchResponse response = new CreateBranchResponse();
        return response;
    }

    @Override
    public DeleteBranchResponse delete(DeleteBranchRequest request) {
        DeleteBranchResponse response = new DeleteBranchResponse();
        return response;
    }

    @Override
    public ToUpdateBranchResponse toUpdate(ToUpdateBranchRequest request) {
        ToUpdateBranchResponse response = new ToUpdateBranchResponse();
        return response;
    }

    @Override
    public UpdateBranchResponse update(UpdateBranchRequest request) {
        UpdateBranchResponse update = new UpdateBranchResponse();
        return update;
    }

    @Override
    public QueryBranchResponse query(QueryBranchRequest request) {
        QueryBranchResponse response = new QueryBranchResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryBranchRecord.builder()
                    .branchId(i)
                    .branchName("分支机构" + i)
                    .cityName("城市" + i)
                    .branchStatus(BranchStatus.ENABLED.getCode())
                    .build());
        }
        return response;
    }

    @Override
    public FetchBranchResponse fetch(FetchBranchRequest request) {
        FetchBranchResponse response = new FetchBranchResponse();
        response.addNode(Node.builder().text("北京总公司").value("1").build());
        response.addNode(Node.builder().text("重庆分公司").value("2").build());
        return response;
    }
}

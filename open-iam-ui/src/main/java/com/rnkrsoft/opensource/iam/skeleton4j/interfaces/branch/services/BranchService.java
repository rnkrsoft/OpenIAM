package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("机构服务")
public interface BranchService {
    @ApidocInterface("创建")
    CreateBranchResponse create(CreateBranchRequest request);
    @ApidocInterface("删除")
    DeleteBranchResponse delete(DeleteBranchRequest request);
    @ApidocInterface("编辑查看")
    ToUpdateBranchResponse toUpdate(ToUpdateBranchRequest request);
    @ApidocInterface("编辑")
    UpdateBranchResponse update(UpdateBranchRequest request);
    @ApidocInterface("查询")
    QueryBranchResponse query(QueryBranchRequest request);
    @ApidocInterface("拉取")
    FetchBranchResponse fetch(FetchBranchRequest request);
}

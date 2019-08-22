package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("岗位服务")
public interface JobService {
    @ApidocInterface("查询")
    QueryJobResponse query(QueryJobRequest request);
    @ApidocInterface("创建")
    CreateJobResponse create(CreateJobRequest request);
    @ApidocInterface("编辑查看")
    ToUpdateJobResponse toUpdate(ToUpdateJobRequest request);
    @ApidocInterface("编辑")
    UpdateJobResponse update(UpdateJobRequest request);
    @ApidocInterface("删除")
    DeleteJobResponse delete(DeleteJobRequest request);
    @ApidocInterface("拉取")
    FetchJobResponse fetch(FetchJobRequest request);
}

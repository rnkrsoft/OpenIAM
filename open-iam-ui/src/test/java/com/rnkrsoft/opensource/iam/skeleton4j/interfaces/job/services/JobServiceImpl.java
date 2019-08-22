package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains.*;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
public class JobServiceImpl implements JobService{
    @Override
    public QueryJobResponse query(QueryJobRequest request) {
        QueryJobResponse response = new QueryJobResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryJobRecord.builder().jobId(i).jobName("岗位" + i).build());
        }
        return response;
    }

    @Override
    public CreateJobResponse create(CreateJobRequest request) {
        CreateJobResponse response = new CreateJobResponse();
        return response;
    }

    @Override
    public ToUpdateJobResponse toUpdate(ToUpdateJobRequest request) {
        ToUpdateJobResponse response = new ToUpdateJobResponse();
        return response;
    }

    @Override
    public UpdateJobResponse update(UpdateJobRequest request) {
        UpdateJobResponse response = new UpdateJobResponse();
        return response;
    }

    @Override
    public DeleteJobResponse delete(DeleteJobRequest request) {
        DeleteJobResponse response = new DeleteJobResponse();
        return response;
    }

    @Override
    public FetchJobResponse fetch(FetchJobRequest request) {
        FetchJobResponse response = new FetchJobResponse();
        response.addNode(Node.builder().text("后端开发工程师").value("1").build());
        response.addNode(Node.builder().text("移动端开发工程师").value("2").build());
        return response;
    }
}

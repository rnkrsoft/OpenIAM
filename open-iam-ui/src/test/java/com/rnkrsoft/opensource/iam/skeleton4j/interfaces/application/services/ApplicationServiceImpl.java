package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services;

import com.rnkrsoft.opensource.iam.enums.ApplicationStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains.*;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
public class ApplicationServiceImpl implements ApplicationService{
    @Override
    public CreateApplicationResponse create(CreateApplicationRequest request) {
        CreateApplicationResponse response = new CreateApplicationResponse();
        return response;
    }

    @Override
    public ToUpdateApplicationResponse toUpdate(ToUpdateApplicationRequest request) {
        ToUpdateApplicationResponse response = new ToUpdateApplicationResponse();
        return response;
    }

    @Override
    public UpdateApplicationResponse update(UpdateApplicationRequest request) {
        UpdateApplicationResponse response = new UpdateApplicationResponse();
        return response;
    }

    @Override
    public QueryApplicationResponse query(QueryApplicationRequest request) {
        QueryApplicationResponse response = new QueryApplicationResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryApplicationRecord.builder()
                    .appId(i)
                    .appCode("" + i)
                    .appTitle("应用系统" + i)
                    .appStatus(ApplicationStatus.ENABLED.getCode())
                    .build());
        }
        return response;
    }

    @Override
    public FetchApplicationResponse fetch(FetchApplicationRequest request) {
        FetchApplicationResponse response = new FetchApplicationResponse();
        response.addNode(Node.builder().text("测试系统1").value("1").build());
        response.addNode(Node.builder().text("测试系统2").value("2").build());
        return response;
    }

    @Override
    public FixApplicationAuthorityResponse fix(FixApplicationAuthorityRequest request) {
        return null;
    }
}

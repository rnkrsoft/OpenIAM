package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.services;

import com.rnkrsoft.opensource.iam.enums.ActionStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains.FetchApplicationResponse;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
public class ActionServiceImpl implements ActionService{
    @Override
    public QueryActionResponse query(QueryActionRequest request) {
        QueryActionResponse response = new QueryActionResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryActionRecord.builder().actionId(i)
                            .applicationTitle("测试系统")
                            .productTitle("测试产品")
                            .actionCode("" + i)
                            .actionTitle("测试功能" + i)
                            .actionState(ActionStatus.ENABLED.getCode())
                            .build()
            );
        }
        return response;
    }

    @Override
    public FetchActionResponse fetch(FetchActionRequest request) {
        FetchActionResponse response = new FetchActionResponse();
        response.addNode(Node.builder().text("测试功能1").value("1").build());
        response.addNode(Node.builder().text("测试功能2").value("2").build());
        return response;
    }
}

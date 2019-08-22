package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.services;

import com.rnkrsoft.opensource.iam.enums.SubActionStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionRecord;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionResponse;

import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
public class SubActionServiceImpl implements SubActionService{
    @Override
    public QuerySubActionResponse query(QuerySubActionRequest request) {
        QuerySubActionResponse response = new QuerySubActionResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QuerySubActionRecord.builder()
                    .subActionId(i)
                    .subActionCode("code" + i)
                    .applicationTitle("应用系统" + i)
                    .productTitle("产品" + i)
                    .actionTitle("功能" + i)
                    .subActionTitle("操作" + i)
                    .subActionStatus(SubActionStatus.ENABLED.getCode())
                    .build());
        }
        return response;
    }
}

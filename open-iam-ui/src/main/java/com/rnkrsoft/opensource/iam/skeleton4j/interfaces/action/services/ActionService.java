package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains.FetchActionRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains.FetchActionResponse;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains.QueryActionRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains.QueryActionResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("功能服务")
public interface ActionService {
    @ApidocInterface("查询")
    QueryActionResponse query(QueryActionRequest request);
    @ApidocInterface("拉取")
    FetchActionResponse fetch(FetchActionRequest request);
}

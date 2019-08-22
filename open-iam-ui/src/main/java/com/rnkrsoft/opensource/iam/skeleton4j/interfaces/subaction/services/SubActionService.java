package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.services;


import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("操作服务")
public interface SubActionService {
    @ApidocInterface("查询")
    QuerySubActionResponse query(QuerySubActionRequest request);
}

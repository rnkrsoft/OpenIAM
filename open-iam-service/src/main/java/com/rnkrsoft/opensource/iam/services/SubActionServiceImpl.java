package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.domains.SubAction;
import com.rnkrsoft.opensource.iam.enums.SubActionStatus;
import com.rnkrsoft.opensource.iam.jdbc.bo.QuerySubActionBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.SubActionDAO;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionRecord;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.domains.QuerySubActionResponse;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.subaction.services.SubActionService;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class SubActionServiceImpl implements SubActionService {
    @Autowired
    SubActionDAO subActionDAO;
    @Override
    public QuerySubActionResponse query(QuerySubActionRequest request) {
        QuerySubActionResponse response = new QuerySubActionResponse();
        Pagination<QuerySubActionBO> pagination = new Pagination<QuerySubActionBO>(request.getPageSize(), request.getPageNo());
        if (StringUtils.isNotBlank(request.getApplicationId())){
            pagination.getParams().put("appId", request.getApplicationId());
        }
        if (StringUtils.isNotBlank(request.getProductId())){
            pagination.getParams().put("productId", request.getProductId());
        }
        if (StringUtils.isNotBlank(request.getActionId())){
            pagination.getParams().put("actionId", request.getActionId());
        }
        if (StringUtils.isNotBlank(request.getSubActionCode())){
            pagination.getParams().put("subActionCode", request.getSubActionCode());
        }
        if (StringUtils.isNotBlank(request.getSubActionStatus())){
            pagination.getParams().put("subActionStatus", request.getSubActionStatus());
        }
        subActionDAO.querySubAction(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QuerySubActionBO record : pagination.getRecords()) {
            response.addRecord(QuerySubActionRecord.builder()
                    .subActionId(record.getSubActionId())
                    .subActionCode(record.getSubActionCode())
                    .applicationTitle(record.getAppTitle())
                    .productTitle(record.getProductTitle())
                    .actionTitle(record.getActionTitle())
                    .subActionTitle(record.getSubActionTitle())
                    .subActionStatus(record.getSubActionStatus())
                    .build());
        }
        return response;
    }
}

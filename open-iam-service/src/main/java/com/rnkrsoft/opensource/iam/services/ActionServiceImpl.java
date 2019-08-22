package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.enums.ActionStatus;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryActionBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryUserBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.ActionDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.ActionEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.action.services.ActionService;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    ActionDAO actionDAO;
    @Override
    public QueryActionResponse query(QueryActionRequest request) {
        QueryActionResponse response = new QueryActionResponse();
        Pagination<QueryActionBO> pagination = new Pagination<QueryActionBO>(request.getPageSize(), request.getPageNo());
        if (StringUtils.isNotBlank(request.getApplicationId())){
            pagination.getParams().put("appId", request.getApplicationId());
        }
        if (StringUtils.isNotBlank(request.getProductId())){
            pagination.getParams().put("productId", request.getProductId());
        }
        if (StringUtils.isNotBlank(request.getActionCode())){
            pagination.getParams().put("actionCode", request.getActionCode());
        }
        if (StringUtils.isNotBlank(request.getActionTitle())){
            pagination.getParams().put("actionTitle", request.getActionTitle());
        }
        if (StringUtils.isNotBlank(request.getActionStatus())){
            pagination.getParams().put("actionStatus", request.getActionStatus());
        }
        actionDAO.queryAction(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QueryActionBO record : pagination.getRecords()) {
            response.addRecord(QueryActionRecord.builder()
                            .actionId(record.getActionId())
                            .applicationTitle(record.getApplicationTitle())
                            .productTitle(record.getProductTitle())
                            .actionCode(record.getActionCode())
                            .actionTitle(record.getActionTitle())
                            .actionState(ActionStatus.ENABLED.getCode())
                            .build()
            );
        }
        return response;
    }

    @Override
    public FetchActionResponse fetch(FetchActionRequest request) {
        FetchActionResponse response = new FetchActionResponse();
        List<ActionEntity> actionEntities = actionDAO.selectAnd(ActionEntity.builder().actionStatus(ActionStatus.ENABLED.getCode()).build());
        for (ActionEntity record: actionEntities){
            response.addNode(Node.builder().text(record.getActionTitle()).value(Integer.toString(record.getActionId())).build());
        }
        return response;
    }
}

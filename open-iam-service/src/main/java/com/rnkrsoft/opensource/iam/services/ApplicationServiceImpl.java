package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.enums.AppStatus;
import com.rnkrsoft.opensource.iam.enums.ApplicationStatus;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.jdbc.dao.ApplicationDAO;
import com.rnkrsoft.opensource.iam.jdbc.dao.AuthorityDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.ApplicationEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;
import java.util.Date;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationDAO applicationDAO;
    @Autowired
    AuthorityDAO authorityDAO;
    @Override
    public CreateApplicationResponse create(CreateApplicationRequest request) {
        CreateApplicationResponse response = new CreateApplicationResponse();
        List<ApplicationEntity> applicationEntities = applicationDAO.selectAnd(ApplicationEntity.builder().appCode(request.getAppCode()).build());
        if (!applicationEntities.isEmpty()){
            response.setRspCode(IAMResponseCode.APP_HAS_EXISTS);
            return response;
        }
        ApplicationEntity applicationEntity = ApplicationEntity.builder()
                .appCode(request.getAppCode())
                .appTitle(request.getAppTitle())
                .appIcon(request.getAppIcon())
                .appUrl(request.getAppUrl())
                .build();
        applicationEntity.setCreateDate(new Date());
        applicationEntity.setLastUpdateDate(new Date());
        applicationDAO.insertSelective(applicationEntity);
        return response;
    }

    @Override
    public ToUpdateApplicationResponse toUpdate(ToUpdateApplicationRequest request) {
        ToUpdateApplicationResponse response = new ToUpdateApplicationResponse();
        ApplicationEntity applicationEntity = applicationDAO.selectByPrimaryKey(request.getAppId());
        if (applicationEntity == null){
            response.setRspCode(IAMResponseCode.APP_NOT_EXISTS);
            return response;
        }
        response.setAppId(applicationEntity.getAppId());
        response.setAppCode(applicationEntity.getAppCode());
        response.setAppTitle(applicationEntity.getAppTitle());
        response.setAppIcon(applicationEntity.getAppIcon());
        response.setAppUrl(applicationEntity.getAppUrl());
        response.setAppStatus(applicationEntity.getAppStatus());
        return response;
    }

    @Override
    public UpdateApplicationResponse update(UpdateApplicationRequest request) {
        UpdateApplicationResponse response = new UpdateApplicationResponse();
        ApplicationEntity applicationEntity = applicationDAO.selectByPrimaryKey(request.getAppId());
        if (applicationEntity == null){
            response.setRspCode(IAMResponseCode.APP_NOT_EXISTS);
            return response;
        }
        applicationEntity = ApplicationEntity.builder()
                .appId(request.getAppId())
                .appTitle(request.getAppTitle())
                .appCode(request.getAppCode())
                .appIcon(request.getAppIcon())
                .appUrl(request.getAppUrl())
                .appStatus(request.getAppStatus())
                .build();
        applicationEntity.setLastUpdateDate(new Date());
        int updateCount = applicationDAO.updateByPrimaryKeySelective(applicationEntity);
        if (updateCount != 1){
            response.setRspCode(IAMResponseCode.UPDATE_APP_FAILURE);
            return response;
        }
        return response;
    }

    @Override
    public QueryApplicationResponse query(QueryApplicationRequest request) {
        QueryApplicationResponse response = new QueryApplicationResponse();
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setAppStatus(request.getAppStatus());
        if (StringUtils.isNotBlank(request.getAppCode())){
            applicationEntity.setAppCode(request.getAppCode());
        }
        if (StringUtils.isNotBlank(request.getAppTitle())){
            applicationEntity.setAppTitle(request.getAppTitle());
        }
        Pagination<ApplicationEntity> pagination = new Pagination<ApplicationEntity>(request.getPageSize(), request.getPageNo(), applicationEntity);
        applicationDAO.selectPageAnd(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (ApplicationEntity record : pagination.getRecords()) {
            response.addRecord(QueryApplicationRecord.builder()
                    .appId(record.getAppId())
                    .appCode(record.getAppCode())
                    .appTitle(record.getAppTitle())
                    .appUrl(record.getAppUrl())
                    .appStatus(record.getAppStatus())
                    .build());
        }
        return response;
    }

    @Override
    public FetchApplicationResponse fetch(FetchApplicationRequest request) {
        FetchApplicationResponse response = new FetchApplicationResponse();
        List<ApplicationEntity> applicationEntities = applicationDAO.selectAnd(ApplicationEntity.builder().appStatus(AppStatus.ENABLED.getCode()).build());
        for (ApplicationEntity applicationEntity : applicationEntities){
            response.addNode(Node.builder().text(applicationEntity.getAppTitle()).value(Integer.toString(applicationEntity.getAppId())).build());
        }
        return response;
    }

    @Override
    public FixApplicationAuthorityResponse fix(FixApplicationAuthorityRequest request) {
        FixApplicationAuthorityResponse response = new FixApplicationAuthorityResponse();
        authorityDAO.fixByAppId(request.getAppId());
        return response;
    }
}

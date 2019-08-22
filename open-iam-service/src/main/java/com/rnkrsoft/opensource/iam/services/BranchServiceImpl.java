package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.enums.BranchStatus;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryBranchBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.BranchDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.BranchEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
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
public class BranchServiceImpl implements BranchService {
    @Autowired
    BranchDAO branchDAO;

    @Override
    public CreateBranchResponse create(CreateBranchRequest request) {
        CreateBranchResponse response = new CreateBranchResponse();
        BranchEntity entity = branchDAO.selectByPrimaryKey(request.getBranchId());
        if (entity != null) {
            response.setRspCode(IAMResponseCode.BRANCH_HAS_EXISTS);
            return response;
        }
        entity = BranchEntity.builder()
                .branchId(request.getBranchId())
                .branchName(request.getBranchName())
                .cityId(request.getCityId())
                .build();
        entity.setCreateDate(new Date());
        entity.setLastUpdateDate(new Date());
        branchDAO.insertSelective(entity);
        return response;
    }

    @Override
    public DeleteBranchResponse delete(DeleteBranchRequest request) {
        DeleteBranchResponse response = new DeleteBranchResponse();
        BranchEntity branchEntity = BranchEntity.builder()
                .branchId(request.getBranchId())
                .branchStatus(BranchStatus.DISABLED.getCode())
                .build();
        branchEntity.setLastUpdateDate(new Date());
        int count = branchDAO.updateByPrimaryKeySelective(branchEntity);
        if (count != 1) {
            response.setRspCode(IAMResponseCode.LOGIC_DELETE_FAILURE);
            return response;
        } else {
            return response;
        }
    }

    @Override
    public ToUpdateBranchResponse toUpdate(ToUpdateBranchRequest request) {
        ToUpdateBranchResponse response = new ToUpdateBranchResponse();
        BranchEntity entity = branchDAO.selectByPrimaryKey(request.getBranchId());
        if (entity == null) {
            response.setRspCode(IAMResponseCode.BRANCH_NOT_EXISTS);
            return response;
        }
        response.setBranchId(entity.getBranchId());
        response.setBranchName(entity.getBranchName());
        response.setCityId(entity.getCityId());
        return response;
    }

    @Override
    public UpdateBranchResponse update(UpdateBranchRequest request) {
        UpdateBranchResponse response = new UpdateBranchResponse();
        BranchEntity branchEntity = BranchEntity.builder()
                .branchId(request.getBranchId())
                .branchName(request.getBranchName())
                .cityId(request.getCityId())
                .build();
        branchEntity.setLastUpdateDate(new Date());
        int count = branchDAO.updateByPrimaryKeySelective(branchEntity);
        if (count != 1) {
            response.setRspCode(IAMResponseCode.UPDATE_BRANCH_FAILURE);
            return response;
        } else {
            return response;
        }
    }

    @Override
    public QueryBranchResponse query(QueryBranchRequest request) {
        QueryBranchResponse response = new QueryBranchResponse();
        Pagination<QueryBranchBO> pagination = new Pagination<QueryBranchBO>(request.getPageSize(), request.getPageNo());
        if (StringUtils.isNotBlank(request.getCityId())){
            pagination.getParams().put("cityId", request.getCityId());
        }
        if (StringUtils.isNotBlank(request.getBranchStatus())){
            pagination.getParams().put("branchStatus", request.getBranchStatus());
        }
        branchDAO.queryBranch(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QueryBranchBO record : pagination.getRecords()) {
            response.addRecord(QueryBranchRecord.builder()
                    .branchId(record.getBranchId())
                    .branchName(record.getBranchName())
                    .cityName(record.getCityName())
                    .branchStatus(record.getBranchStatus())
                    .build());
        }
        return response;
    }

    @Override
    public FetchBranchResponse fetch(FetchBranchRequest request) {
        FetchBranchResponse response = new FetchBranchResponse();
        List<BranchEntity> branchEntities = branchDAO.selectAnd(BranchEntity.builder().branchStatus(BranchStatus.ENABLED.getCode()).build());
        for (BranchEntity record : branchEntities){
            response.addNode(Node.builder().text(record.getBranchName()).value(Integer.toString(record.getBranchId())).build());
        }
        return response;
    }
}

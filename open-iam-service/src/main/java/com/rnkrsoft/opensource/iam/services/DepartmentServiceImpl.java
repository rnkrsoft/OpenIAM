package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.enums.DepartmentStatus;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryDepartmentBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.DepartmentDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.DepartmentEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.services.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;

    @Override
    public CreateDepartmentResponse create(CreateDepartmentRequest request) {
        CreateDepartmentResponse response = new CreateDepartmentResponse();
        DepartmentEntity departmentEntity = DepartmentEntity.builder()
                .departmentId(request.getDepartmentId())
                .departmentName(request.getDepartmentName())
                .branchId(request.getBranchId())
                .departmentStatus(DepartmentStatus.ENABLED.getCode())
                .build();
        departmentEntity.setCreateDate(new Date());
        departmentEntity.setLastUpdateDate(new Date());
        departmentDAO.insertSelective(departmentEntity);
        return response;
    }

    @Override
    public DeleteDepartmentResponse delete(DeleteDepartmentRequest request) {
        DeleteDepartmentResponse response = new DeleteDepartmentResponse();
        DepartmentEntity departmentEntity = DepartmentEntity.builder()
                .departmentId(request.getDepartmentId())
                .departmentStatus(DepartmentStatus.DISABLED.getCode())
                .build();
        departmentEntity.setLastUpdateDate(new Date());
        int count = departmentDAO.updateByPrimaryKeySelective(departmentEntity);
        if (count == 1) {
            return response;
        } else {
            response.setRspCode(IAMResponseCode.LOGIC_DELETE_FAILURE);
            return response;
        }

    }

    @Override
    public ToUpdateDepartmentResponse toUpdate(ToUpdateDepartmentRequest request) {
        ToUpdateDepartmentResponse response = new ToUpdateDepartmentResponse();
        DepartmentEntity entity = departmentDAO.selectByPrimaryKey(request.getDepartmentId());
        if (entity == null) {
            response.setRspCode(IAMResponseCode.DEPARTMENT_NOT_EXISTS);
            return response;
        }
        response.setDepartmentId(entity.getDepartmentId());
        response.setDepartmentName(entity.getDepartmentName());
        response.setBranchId(entity.getBranchId());
        return response;
    }

    @Override
    public UpdateDepartmentResponse update(UpdateDepartmentRequest request) {
        UpdateDepartmentResponse response = new UpdateDepartmentResponse();
        DepartmentEntity departmentEntity = DepartmentEntity.builder()
                .departmentId(request.getDepartmentId())
                .departmentName(request.getDepartmentName())
                .build();
        departmentEntity.setLastUpdateDate(new Date());
        int count = departmentDAO.updateByPrimaryKeySelective(departmentEntity);
        if (count != 1) {
            response.setRspCode(IAMResponseCode.UPDATE_DEPARTMENT_FAILURE);
            return response;
        } else {
            return response;
        }
    }

    @Override
    public QueryDepartmentResponse query(QueryDepartmentRequest request) {
        QueryDepartmentResponse response = new QueryDepartmentResponse();
        Pagination<QueryDepartmentBO> pagination = new Pagination<QueryDepartmentBO>(request.getPageSize(), request.getPageNo());
        if (StringUtils.isNotBlank(request.getBranchId())) {
            pagination.getParams().put("branchId", request.getBranchId());
        }
        if (StringUtils.isNotBlank(request.getCityId())) {
            pagination.getParams().put("cityId", request.getCityId());
        }
        pagination.getParams().put("departmentStatus", request.getDepartmentStatus());
        departmentDAO.queryDepartment(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QueryDepartmentBO record : pagination.getRecords()) {
            response.addRecord(QueryDepartmentRecord.builder()
                    .departmentId(record.getDepartmentId())
                    .departmentName(record.getDepartmentName())
                    .branchName(record.getBranchName())
                    .cityName(record.getCityName())
                    .departmentStatus(record.getDepartmentStatus())
                    .build());
        }
        return response;
    }

    @Override
    public FetchDepartmentResponse fetch(FetchDepartmentRequest request) {
        FetchDepartmentResponse response = new FetchDepartmentResponse();
        List<DepartmentEntity> departmentEntities = departmentDAO.selectAnd(DepartmentEntity.builder().departmentStatus(DepartmentStatus.ENABLED.getCode()).build());
        for (DepartmentEntity record : departmentEntities) {
            response.addNode(Node.builder().text(record.getDepartmentName()).value(Integer.toString(record.getDepartmentId())).build());
        }
        return response;
    }
}

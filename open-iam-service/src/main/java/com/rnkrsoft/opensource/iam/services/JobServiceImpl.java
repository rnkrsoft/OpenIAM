package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.enums.JobStatus;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryJobBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.JobDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.JobEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.services.JobService;
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
public class JobServiceImpl implements JobService {
    @Autowired
    JobDAO jobDAO;

    @Override
    public QueryJobResponse query(QueryJobRequest request) {
        QueryJobResponse response = new QueryJobResponse();
        Pagination<QueryJobBO> pagination = new Pagination<QueryJobBO>(request.getPageSize(), request.getPageNo());
        pagination.getParams().put("branchId", request.getBranchId());
        pagination.getParams().put("jobStatus", request.getJobStatus());
        jobDAO.queryJob(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QueryJobBO record : pagination.getRecords()) {
            response.addRecord(QueryJobRecord.builder()
                    .jobId(record.getJobId())
                    .jobName(record.getJobName())
                    .jobStatus(record.getJobStatus())
                    .departmentName(record.getDepartmentName())
                    .branchName(record.getBranchName())
                    .cityName(record.getCityName())
                    .build());
        }
        return response;
    }

    @Override
    public CreateJobResponse create(CreateJobRequest request) {
        CreateJobResponse response = new CreateJobResponse();
        JobEntity jobEntity = JobEntity.builder()
                .jobName(request.getJobName())
                .departmentId(request.getDepartmentId())
                .jobStatus(request.getJobStatus())
                .build();
        jobEntity.setCreateDate(new Date());
        jobEntity.setLastUpdateDate(new Date());
        jobDAO.insertSelective(jobEntity);
        return response;
    }

    @Override
    public ToUpdateJobResponse toUpdate(ToUpdateJobRequest request) {
        ToUpdateJobResponse response = new ToUpdateJobResponse();
        response.setJobId(request.getJobId());
        JobEntity jobEntity = jobDAO.selectByPrimaryKey(request.getJobId());
        if (jobEntity == null) {
            response.setRspCode(IAMResponseCode.JOB_NOT_EXISTS);
            return response;
        }
        response.setDepartmentId(jobEntity.getDepartmentId());
        response.setJobName(jobEntity.getJobName());
        response.setJobStatus(jobEntity.getJobStatus());
        return response;
    }

    @Override
    public UpdateJobResponse update(UpdateJobRequest request) {
        UpdateJobResponse response = new UpdateJobResponse();
        JobEntity jobEntity = jobDAO.selectByPrimaryKey(request.getJobId());
        if (jobEntity == null) {
            response.setRspCode(IAMResponseCode.JOB_NOT_EXISTS);
            return response;
        }
        jobEntity = JobEntity.builder()
                .jobId(request.getJobId())
                .jobName(request.getJobName())
                .departmentId(request.getDepartmentId())
                .jobStatus(request.getJobStatus())
                .build();
        jobEntity.setLastUpdateDate(new Date());
        jobDAO.updateByPrimaryKeySelective(jobEntity);
        return response;
    }

    @Override
    public DeleteJobResponse delete(DeleteJobRequest request) {
        DeleteJobResponse response = new DeleteJobResponse();
        JobEntity jobEntity = jobDAO.selectByPrimaryKey(request.getJobId());
        if (jobEntity == null) {
            response.setRspCode(IAMResponseCode.JOB_NOT_EXISTS);
            return response;
        }
        jobDAO.deleteByPrimaryKey(request.getJobId());
        return response;
    }

    @Override
    public FetchJobResponse fetch(FetchJobRequest request) {
        FetchJobResponse response = new FetchJobResponse();
        List<JobEntity> jobEntities = jobDAO.selectAnd(JobEntity.builder().jobStatus(JobStatus.ENABLED.getCode()).build());
        for (JobEntity record : jobEntities) {
            response.addNode(Node.builder().text(record.getJobName()).value(Integer.toString(record.getJobId())).build());
        }
        return response;
    }
}

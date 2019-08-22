package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.opensource.iam.cache.dao.SessionCache;
import com.rnkrsoft.opensource.iam.cache.dao.SmsCache;
import com.rnkrsoft.opensource.iam.cache.dao.UserUuidCache;
import com.rnkrsoft.opensource.iam.cache.entity.SessionEntity;
import com.rnkrsoft.opensource.iam.internal.config.IAMConfig;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.enums.UserStatus;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.CheckQrCodeRequest;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.CheckQrCodeResponse;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.GenerateQrCodeRequest;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.GenerateQrCodeResponse;
import com.rnkrsoft.opensource.iam.internal.qrcode.services.QrCodeLoginService;
import com.rnkrsoft.opensource.iam.jdbc.bo.SelectJobBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.BranchDAO;
import com.rnkrsoft.opensource.iam.jdbc.dao.DepartmentDAO;
import com.rnkrsoft.opensource.iam.jdbc.dao.JobDAO;
import com.rnkrsoft.opensource.iam.jdbc.dao.UserDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.UserEntity;
import com.rnkrsoft.opensource.iam.vo.Branch;
import com.rnkrsoft.opensource.iam.vo.City;
import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.opensource.iam.vo.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Service
public class QrCodeLoginServiceImpl implements QrCodeLoginService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    DepartmentDAO departmentDAO;
    @Autowired
    BranchDAO branchDAO;
    @Autowired
    JobDAO jobDAO;
    @Autowired
    SmsCache smsCache;
    @Autowired
    SessionCache sessionCache;
    @Autowired
    UserUuidCache userUuidCache;
    @Autowired
    IAMConfig iamConfig;

    @Override
    public GenerateQrCodeResponse generate(GenerateQrCodeRequest request) {
        GenerateQrCodeResponse response = new GenerateQrCodeResponse();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userUuidCache.createUuid(uuid, "wait");
        userUuidCache.expireUuid(uuid, 60);
        response.setUuid(uuid);
        return response;
    }

    @Override
    public CheckQrCodeResponse check(CheckQrCodeRequest request) {
        CheckQrCodeResponse response = new CheckQrCodeResponse();
        String newToken = userUuidCache.getUuid(request.getUuid());
        if (newToken == null){
            response.setRspCode(IAMResponseCode.QRCODE_INVALIDATION);
            return response;
        }
        if ("wait".equals(newToken)){
            response.setRspCode(IAMResponseCode.WAIT_FOR_AUTHORITY);
            return response;
        }
        SessionEntity newSession = sessionCache.getSession(newToken);
        if (newSession == null) {
            response.setRspCode(IAMResponseCode.SESSION_INVALIDATION);
            return response;
        }
        //获取用户信息
        UserEntity userEntity = userDAO.selectByPrimaryKey(newSession.getUserId());
        if (userEntity == null) {
            //用户不存在
            response.setRspCode(IAMResponseCode.USER_NOT_EXISTS);
            return response;
        }
        if (UserStatus.NORMAL != UserStatus.valueOfCode(userEntity.getUserStatus())) {
            //用户处于异常状态
            response.setRspCode(IAMResponseCode.USER_ABNORMAL_STATE);
            return response;
        }
        List<SelectJobBO> jobs = jobDAO.selectJobByUserId(userEntity.getUserId());
        List<Department> departmentList = new ArrayList();
        Department currentDepartment = null;
        for (SelectJobBO record : jobs) {
            Department department = Department.builder()
                    .branch(Branch.builder().branchId(record.getBranchId()).branchName(record.getBranchName()).build())
                    .city(City.builder().cityId(record.getCityId()).cityName(record.getCityName()).build())
                    .Job(Job.builder().jobId(record.getJobId()).jobName(record.getJobName()).build())
                    .departmentId(record.getDepartmentId())
                    .departmentName(record.getDepartmentName())
                    .build();
            departmentList.add(department);
            //如果数据库记录的最后登录部门，则将当前部门设置为最后登录的部门
            if (userEntity.getLatestDepartmentId().compareTo(record.getDepartmentId()) == 0) {
                currentDepartment = department;
            }
        }
        response = CheckQrCodeResponse.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .nickName(userEntity.getNickName())
                .userAvatar(userEntity.getUserAvatar())
                .mobileNo(userEntity.getMobileNo())
                .channel(newSession.getChannel())
                .token(newToken)
                .currentDepartment(currentDepartment)
                .build();
        response.getDepartments().addAll(departmentList);
        //删除随即用户识别码
        userUuidCache.remove(request.getUuid());
        return response;
    }
}

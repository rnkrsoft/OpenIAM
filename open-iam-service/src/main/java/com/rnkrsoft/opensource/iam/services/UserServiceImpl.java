package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.cache.dao.AuthorityCache;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.enums.UserStatus;
import com.rnkrsoft.opensource.iam.jdbc.bo.SelectJobBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryUserBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.*;
import com.rnkrsoft.opensource.iam.jdbc.entity.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.services.UserService;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.web.skeleton4j.utils.QueryUtils;
import java.util.*;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    DepartmentDAO departmentDAO;
    @Autowired
    JobDAO jobDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    UserJobDAO userJobDAO;
    @Autowired
    UserRoleDAO userRoleDAO;
    @Autowired
    AuthorityCacheHelperService authorityCacheHelperService;

    @Override
    public QueryUserResponse query(QueryUserRequest request) {
        QueryUserResponse response = new QueryUserResponse();
        Pagination<QueryUserBO> pagination = new Pagination<QueryUserBO>(request.getPageSize(), request.getPageNo());
        if (StringUtils.isNotBlank(request.getUserStatus())) {
            pagination.getParams().put("userStatus", request.getUserStatus());
        }
        if (StringUtils.isNotBlank(request.getUserName())) {
            pagination.getParams().put("userName", request.getUserName());
        }
        if (StringUtils.isNotBlank(request.getNickName())) {
            pagination.getParams().put("nickName", request.getNickName());
        }
        if (StringUtils.isNotBlank(request.getMobileNo())) {
            pagination.getParams().put("mobileNo", request.getMobileNo());
        }

        userDAO.queryUser(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QueryUserBO record : pagination.getRecords()) {
            response.addRecord(QueryUserRecord.builder()
                    .userId(record.getUserId())
                    .userName(record.getUserName())
                    .nickName(record.getNickName())
                    .userAvatar(record.getUserAvatar())
                    .mobileNo(record.getMobileNo())
                    .departmentName(record.getDepartments())
                    .roleName(record.getRoles())
                    .branchName(record.getBranches())
                    .jobName(record.getJobs())
                    .status(record.getUserStatus())
                    .build());
        }
        return response;
    }

    @Override
    public CreateUserResponse create(CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        if (StringUtils.isNotBlank(request.getUserId())) {
           UserEntity userEntity = userDAO.selectByPrimaryKey(request.getUserId());
            if (userEntity != null){
                response.setRspCode(IAMResponseCode.USER_HAS_EXISTS);
                return response;
            }
        }
        int count = userDAO.countAnd(UserEntity.builder().mobileNo(request.getMobileNo()).build());
        if (count > 0) {
            response.setRspCode(IAMResponseCode.USER_HAS_EXISTS);
            return response;
        }
        UserEntity userEntity = UserEntity.builder()
                .mobileNo(request.getMobileNo())
                .userName(request.getUserName())
                .nickName(request.getNickName())
                .userAvatar(request.getUserAvatar())
                .userStatus(UserStatus.NORMAL.getCode())
                .firstLogin(true)
                .build();
        if (StringUtils.isNotBlank(request.getUserId())){
            userEntity.setUserId(request.getUserId());
        }
        userEntity.setCreateDate(new Date());
        userEntity.setLastUpdateDate(new Date());
        userDAO.insertSelective(userEntity);
        for (Integer id : request.getJobs()){
            userJobDAO.insertSelective(UserJobEntity.builder().userId(userEntity.getUserId()).jobId(id).build());
        }
        for (Integer id : request.getRoles()) {
            userRoleDAO.insertSelective(UserRoleEntity.builder().userId(userEntity.getUserId()).roleId(id).build());
        }
        return response;
    }

    @Override
    public ViewUserResponse view(ViewUserRequest request) {
        ViewUserResponse response = new ViewUserResponse();
        UserEntity userEntity = userDAO.selectByPrimaryKey(request.getUserId());
        if (userEntity == null) {
            response.setRspCode(IAMResponseCode.USER_NOT_EXISTS);
            return response;
        }
        List<SelectJobBO> selectJobBOs = jobDAO.selectJobByUserId(request.getUserId());
        response.setUserId(userEntity.getUserId());
        response.setUserName(userEntity.getUserName());
        response.setNickName(userEntity.getNickName());
        response.setMobileNo(userEntity.getMobileNo());
        response.setUserAvatar(userEntity.getUserAvatar());
        response.setUserStatus(userEntity.getUserStatus());
        for (SelectJobBO record : selectJobBOs){
            response.getJobs().add(ViewUserJobRecord.builder()
                    .jobMappingId(record.getMappingId())
                    .jobName(record.getJobName())
                    .departmentName(record.getDepartmentName())
                    .build());
        }
        return response;
    }

    @Override
    public ToUpdateUserResponse toUpdate(ToUpdateUserRequest request) {
        ToUpdateUserResponse response = new ToUpdateUserResponse();
        response.setUserId(request.getUserId());
        UserEntity userEntity = userDAO.selectByPrimaryKey(request.getUserId());
        if (userEntity == null) {
            response.setRspCode(IAMResponseCode.USER_NOT_EXISTS);
            return response;
        }
        List<JobEntity> jobEntities = jobDAO.selectByUserId(userEntity.getUserId());
        List<RoleEntity> roleEntities = roleDAO.selectByUserId(userEntity.getUserId());
        response.setUserId(userEntity.getUserId());
        response.setUserName(userEntity.getUserName());
        response.setNickName(userEntity.getNickName());
        response.setMobileNo(userEntity.getMobileNo());
        response.setUserAvatar(userEntity.getUserAvatar());
        response.setUserStatus(userEntity.getUserStatus());
        for (JobEntity jobEntity : jobEntities) {
            response.getJobs().add(jobEntity.getJobId());
        }
        for (RoleEntity roleEntity : roleEntities) {
            response.getRoles().add(roleEntity.getRoleId());
        }
        return response;
    }

    @Override
    @Transactional
    public UpdateUserResponse update(UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        UserEntity userEntity = userDAO.selectByPrimaryKey(request.getUserId());
        if (userEntity == null) {
            response.setRspCode(IAMResponseCode.USER_NOT_EXISTS);
            return response;
        }
        userEntity = UserEntity.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .userAvatar(request.getUserAvatar())
                .nickName(request.getNickName())
                .mobileNo(request.getMobileNo())
                .build();
        userEntity.setLastUpdateDate(new Date());
        int updateCount = userDAO.updateByPrimaryKeySelective(userEntity);
        if (updateCount != 1) {
            response.setRspCode(IAMResponseCode.UPDATE_USER_FAILURE);
            return response;
        }
        Set<Integer> newJobs = request.getJobs();
        Set<Integer> newRoles = request.getRoles();

        Set<Integer> roles = new HashSet();
        Set<Integer> jobs = new HashSet();

        List<UserJobEntity> userJobEntities = userJobDAO.selectAnd(UserJobEntity.builder().userId(request.getUserId()).build());
        List<UserRoleEntity> userRoleEntities = userRoleDAO.selectAnd(UserRoleEntity.builder().userId(request.getUserId()).build());

        //删除岗位
        for (UserJobEntity userJobEntity : userJobEntities) {
            if (newJobs.contains(userJobEntity.getJobId())) {
                //nothing
                jobs.add(userJobEntity.getJobId());
            } else {
                userJobDAO.deleteByPrimaryKey(userJobEntity.getMappingId());
            }
        }

        //删除角色
        for (UserRoleEntity userRoleEntity : userRoleEntities) {
            if (newRoles.contains(userRoleEntity.getRoleId())) {
                //nothing
                roles.add(userRoleEntity.getRoleId());
            } else {
                userRoleDAO.deleteByPrimaryKey(userRoleEntity.getMappingId());
            }
        }

        newJobs.removeAll(jobs);
        newRoles.removeAll(roles);
        //剩下的就是新增加的
        for (Integer id : newJobs) {
            userJobDAO.insertSelective(UserJobEntity.builder().userId(request.getUserId()).jobId(id).build());
        }
        for (Integer id : newRoles) {
            userRoleDAO.insertSelective(UserRoleEntity.builder().userId(request.getUserId()).roleId(id).build());
        }
        authorityCacheHelperService.clear(request.getUserId() + "@*");
        return response;
    }

    @Override
    public EnableUserResponse enable(EnableUserRequest request) {
        EnableUserResponse response = new EnableUserResponse();
        UserEntity userEntity = UserEntity.builder().userId(request.getUserId()).userStatus(UserStatus.NORMAL.getCode()).build();
        userEntity.setLastUpdateDate(new Date());
        int updateCount = userDAO.updateByPrimaryKeySelective(userEntity);
        if (updateCount != 1) {
            response.setRspCode(IAMResponseCode.UPDATE_USER_FAILURE);
            return response;
        }
        authorityCacheHelperService.clear(request.getUserId() + "@*");
        return response;
    }

    @Override
    public DisableUserResponse disable(DisableUserRequest request) {
        DisableUserResponse response = new DisableUserResponse();
        UserEntity userEntity = UserEntity.builder().userId(request.getUserId()).userStatus(UserStatus.DISABLED.getCode()).build();
        userEntity.setLastUpdateDate(new Date());
        int updateCount = userDAO.updateByPrimaryKeySelective(userEntity);
        if (updateCount != 1) {
            response.setRspCode(IAMResponseCode.UPDATE_USER_FAILURE);
            return response;
        }
        authorityCacheHelperService.clear(request.getUserId() + "@*");
        return response;
    }

    @Override
    public LogicDeleteUserResponse logicDelete(LogicDeleteUserRequest request) {
        LogicDeleteUserResponse response = new LogicDeleteUserResponse();
        UserEntity userEntity = UserEntity.builder().userId(request.getUserId()).userStatus(UserStatus.DELETED.getCode()).build();
        userEntity.setLastUpdateDate(new Date());
        int updateCount = userDAO.updateByPrimaryKeySelective(userEntity);
        if (updateCount != 1) {
            response.setRspCode(IAMResponseCode.UPDATE_USER_FAILURE);
            return response;
        }
        authorityCacheHelperService.clear(request.getUserId() + "@*");
        return response;
    }

}

package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.services;

import com.rnkrsoft.opensource.iam.enums.UserStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains.*;

import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
public class UserServiceImpl implements UserService{
    @Override
    public QueryUserResponse query(QueryUserRequest request) {
        QueryUserResponse response = new QueryUserResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryUserRecord.builder()
                    .userId("" + i)
                    .userName("测试用户" + i)
                    .mobileNo("185000000" + i)
                    .departmentName("信息中心,运营中心")
                    .roleName("研发工程师,一般员工")
                    .branchName("昆明,重庆")
                    .jobName("研发工程师,一般员工")
                    .status(UserStatus.valueOfCode(i % 3).getCode())
                    .build());
        }
        return response;
    }

    @Override
    public CreateUserResponse create(CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        return response;
    }

    @Override
    public ViewUserResponse view(ViewUserRequest request) {
        ViewUserResponse response = new ViewUserResponse();
        return response;
    }

    @Override
    public ToUpdateUserResponse toUpdate(ToUpdateUserRequest request) {
        ToUpdateUserResponse response = new ToUpdateUserResponse();
        response.setUserId(request.getUserId());
        return response;
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        return response;
    }

    @Override
    public EnableUserResponse enable(EnableUserRequest request) {
        EnableUserResponse response = new EnableUserResponse();
        return response;
    }

    @Override
    public DisableUserResponse disable(DisableUserRequest request) {
        DisableUserResponse response = new DisableUserResponse();
        return response;
    }

    @Override
    public LogicDeleteUserResponse logicDelete(LogicDeleteUserRequest request) {
        LogicDeleteUserResponse response = new LogicDeleteUserResponse();
        return response;
    }

  }

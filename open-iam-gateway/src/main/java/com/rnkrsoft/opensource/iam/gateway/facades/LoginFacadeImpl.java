package com.rnkrsoft.opensource.iam.gateway.facades;

import com.rnkrsoft.opensource.iam.domains.*;
import com.rnkrsoft.opensource.iam.enums.ChannelType;
import com.rnkrsoft.opensource.iam.gateway.login.domains.*;
import com.rnkrsoft.opensource.iam.gateway.login.facade.LoginFacade;
import com.rnkrsoft.opensource.iam.services.IAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Service
public class LoginFacadeImpl implements LoginFacade {
    @Autowired
    IAMService iamService;
    @Override
    public SmsLoginResponse smsLogin(SmsLoginRequest request) {
        SmsLoginResponse response = new SmsLoginResponse();
        LoginResponse loginResponse = iamService.login(LoginRequest.builder()
                .mobileNo(request.getMobileNo())
                .code(request.getSmsCode())
                .channel(ChannelType.APP.getCode())
                .build());
        if (!loginResponse.success()){
            response.setRspCode(loginResponse.getRspCode());
            response.setRspDesc(loginResponse.getRspDesc());
            return response;
        }
        response = SmsLoginResponse.builder()
                .mobileNo(loginResponse.getMobileNo())
                .channel(loginResponse.getChannel())
                .nickName(loginResponse.getNickName())
                .userId(loginResponse.getUserId())
                .userName(loginResponse.getUserName())
                .nickName(loginResponse.getNickName())
                .token(loginResponse.getToken())
                .userAvatar(loginResponse.getUserAvatar())
                .fistLogin(loginResponse.getFistLogin())
                .currentDepartment(loginResponse.getCurrentDepartment())
                .build();
        response.getDepartments().addAll(loginResponse.getDepartments());
        response.getRoles().addAll(loginResponse.getRoles());
        return response;
    }

    @Override
    public CheckUuidResponse checkUuid(CheckUuidRequest request) {
        CheckUuidResponse response = new CheckUuidResponse();
        TestAuthorizeUserResponse testAuthorizeUserResponse = iamService.testAuthorizeUser(TestAuthorizeUserRequest.builder()
                        .channel(request.getChannel())
                        .uuid(request.getUuid())
                        .token(request.getToken())
                        .userId(request.getUserId())
                        .userName(request.getUserName())
                        .build()
        );
        if (!testAuthorizeUserResponse.success()){
            response.setRspCode(testAuthorizeUserResponse.getRspCode());
            response.setRspDesc(testAuthorizeUserResponse.getRspDesc());
            return response;
        }
        return response;
    }

    @Override
    public BindLoginResponse bindLogin(BindLoginRequest request) {
        BindLoginResponse response = new BindLoginResponse();
        AuthorizeUserResponse authorizeUserResponse = iamService.authorizeUser(AuthorizeUserRequest.builder()
                        .channel(request.getChannel())
                        .uuid(request.getUuid())
                        .token(request.getToken())
                        .userId(request.getUserId())
                        .userName(request.getUserName())
                        .build()
        );
        if (!authorizeUserResponse.success()){
            response.setRspCode(authorizeUserResponse.getRspCode());
            response.setRspDesc(authorizeUserResponse.getRspDesc());
            return response;
        }
        return response;
    }
}

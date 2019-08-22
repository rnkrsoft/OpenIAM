package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.opensource.iam.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsofrt on 2019/7/7.
 */
@ApidocService("身份识别与访问管理服务")
public interface IAMService {
    @ApidocInterface("获取权限")
    FetchAuthorityResponse fetchAuthority(FetchAuthorityRequest request);
    @ApidocInterface("校验是否登录")
    ValidateLoginResponse validateLogin(ValidateLoginRequest request);
    @ApidocInterface("发送随机验证码")
    SendSmsResponse send(SendSmsRequest request);
    @ApidocInterface("用户登录")
    LoginResponse login(LoginRequest request);
    @ApidocInterface("注销登录")
    LogoutResponse logout(LogoutRequest request);
    @ApidocInterface("获取应用")
    FetchApplicationResponse fetchApplication(FetchApplicationRequest request);
    @ApidocInterface("获取配置")
    FetchConfigResponse fetchConfig(FetchConfigRequest request);
    @ApidocInterface(value = "测试是否能够给用户授权", usage = "调用用户授权登录之前调用该接口进行测试")
    TestAuthorizeUserResponse testAuthorizeUser(TestAuthorizeUserRequest request);
    @ApidocInterface(value = "用户授权登录", usage = "调用该接口将传入的用户标识和已登录用户进行绑定,产生新的登录授权")
    AuthorizeUserResponse authorizeUser(AuthorizeUserRequest request);
    @ApidocInterface("切换部门")
    ChangeDepartmentResponse changeDepartment(ChangeDepartmentRequest request);
    @ApidocInterface("获取用户信息")
    GetUserResponse getUser(GetUserRequest request);
    @ApidocInterface("获取用户列表")
    FetchUsersResponse fetchUsers(FetchUsersRequest request);
}

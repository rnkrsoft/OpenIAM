package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.opensource.iam.IAMConfigKey;
import com.rnkrsoft.opensource.iam.cache.dao.SessionCache;
import com.rnkrsoft.opensource.iam.cache.dao.SmsCache;
import com.rnkrsoft.opensource.iam.cache.dao.UserUuidCache;
import com.rnkrsoft.opensource.iam.cache.entity.SessionEntity;
import com.rnkrsoft.opensource.iam.cache.entity.SmsCodeEntity;
import com.rnkrsoft.opensource.iam.cache.entity.UserAuthorityCacheEntity;
import com.rnkrsoft.opensource.iam.internal.config.IAMConfig;
import com.rnkrsoft.opensource.iam.domains.*;
import com.rnkrsoft.opensource.iam.enums.*;
import com.rnkrsoft.opensource.iam.internal.sms.services.SmsService;
import com.rnkrsoft.opensource.iam.jdbc.bo.FetchAuthorityByAppAndUserBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.SelectJobBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.*;
import com.rnkrsoft.opensource.iam.jdbc.entity.ApplicationEntity;
import com.rnkrsoft.opensource.iam.jdbc.entity.UserEntity;
import com.rnkrsoft.opensource.iam.vo.Branch;
import com.rnkrsoft.opensource.iam.vo.City;
import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.opensource.iam.vo.Job;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.data.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Slf4j
@Service("iamService")
public class IAMServiceImpl implements IAMService {
    @Autowired
    IAMConfig iamConfig;
    @Autowired
    SessionCache sessionCache;
    @Autowired
    UserUuidCache userUuidCache;
    @Autowired
    SmsCache smsCache;
    @Autowired
    ApplicationDAO applicationDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    DepartmentDAO departmentDAO;
    @Autowired
    BranchDAO branchDAO;
    @Autowired
    JobDAO jobDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired(required = false)
    SmsService smsService;
    @Autowired
    AuthorityCacheHelperService authorityCacheHelperService;

    @Override
    public FetchAuthorityResponse fetchAuthority(FetchAuthorityRequest request) {
        FetchAuthorityResponse response = new FetchAuthorityResponse();
        if (request == null) {
            response.setRspCode(IAMResponseCode.FAIL);
            response.setRspDesc("请求对象为null");
            return response;
        }
        MDC.put("sessionId", request.getSessionId());
        List<FetchAuthorityByAppAndUserBO> authorities = null;
        //如果使用权限缓存
        if (iamConfig.isUseAuthorityCache()) {
            UserAuthorityCacheEntity userAuthorityCacheEntity = authorityCacheHelperService.get(request.getUserId(), request.getAppCode());
            if (userAuthorityCacheEntity == null || userAuthorityCacheEntity.getRecords().isEmpty()) {
                if (log.isDebugEnabled()) {
                    log.debug("non hit cache {}@{}!", request.getUserId(), request.getAppCode());
                }
                //如果缓存不存在
                authorities = authorityDAO.fetchAuthorityByAppAndUser(request.getAppCode(), request.getUserId());
                userAuthorityCacheEntity = new UserAuthorityCacheEntity();
                userAuthorityCacheEntity.getRecords().addAll(authorities);
                authorityCacheHelperService.store(request.getUserId(), request.getAppCode(), userAuthorityCacheEntity);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("hit cache {}@{}! authorities:{}", request.getUserId(), request.getAppCode(), authorities);
                }
                authorities = userAuthorityCacheEntity.getRecords();
            }
        } else {
            //如果不使用缓存
            authorities = authorityDAO.fetchAuthorityByAppAndUser(request.getAppCode(), request.getUserId());
        }
        for (FetchAuthorityByAppAndUserBO record : authorities) {
            response.getAuthorities().add(AuthorityRecord.builder()
                            .path(record.getAppCode() + "/" + record.getProductCode() + "/" + record.getActionCode() + "/" + record.getSubActionCode())
                            .appCode(record.getAppCode())
                            .appName(record.getAppTitle())
                            .productCode(record.getProductCode())
                            .productName(record.getProductTitle())
                            .actionCode(record.getActionCode())
                            .actionName(record.getActionTitle())
                            .subActionCode(record.getSubActionCode())
                            .subActionName(record.getSubActionTitle())
                            .build()
            );
        }
        //刷新登录会话
        sessionCache.expireSession(request.getToken(), 10 * 60);
        MDC.put("sessionId", null);
        return response;
    }

    @Override
    public ValidateLoginResponse validateLogin(ValidateLoginRequest request) {
        ValidateLoginResponse response = new ValidateLoginResponse();
        SessionEntity session = sessionCache.getSession(request.getToken());
        if (session == null) {
            response.setRspCode(IAMResponseCode.SESSION_INVALIDATION);
            return response;
        }
        response.setUserId(session.getUserId());
        response.setUserName(session.getUserName());
        response.setChannel(session.getChannel());
        return response;
    }

    @Override
    public SendSmsResponse send(SendSmsRequest request) {
        SendSmsResponse response = new SendSmsResponse();
        List<UserEntity> userEntities = userDAO.selectAnd(UserEntity.builder()
                .mobileNo(request.getMobileNo())
                .build());
        if (userEntities.isEmpty()) {
            response.setRspCode(IAMResponseCode.USER_NOT_EXISTS);
            return response;
        }
        UserEntity userEntity = userEntities.get(0);
        if (UserStatus.NORMAL != UserStatus.valueOfCode(userEntity.getUserStatus())) {
            response.setRspCode(IAMResponseCode.USER_ABNORMAL_STATE);
            return response;
        }
        SmsCodeEntity smsCodeEntity = smsCache.get(request.getMobileNo());
        if (smsCodeEntity != null) {
            response.setRspCode(IAMResponseCode.SEND_SMS_TOO_OFTEN);
            return response;
        }
        smsCodeEntity = SmsCodeEntity.builder()
                .mobileNo(request.getMobileNo())
                .smsCode(generate(6))
                .build();
        smsCache.createSmsCode(request.getMobileNo(), smsCodeEntity);
        if (iamConfig.isDebug()) {
            new MockSmsService().sendValidateMessage(request.getMobileNo(), smsCodeEntity.getSmsCode());
            response.setSmsCode(smsCodeEntity.getSmsCode());
            response.setRspCode(IAMResponseCode.DEBUG_MODE);
            response.setRspDesc(smsCodeEntity.getSmsCode());
        } else {
            //发送短信
            if (smsService != null) {
                try {
                    smsService.sendValidateMessage(request.getMobileNo(), smsCodeEntity.getSmsCode());
                } catch (Exception e) {
                    response.setRspCode(IAMResponseCode.SEND_SMS_FAILURE);
                }
            } else {
                response.setRspCode(IAMResponseCode.SMS_SERVICE_NOT_CONFIG);
            }
        }
        return response;
    }

    String generate(int len) {
        String res = "";
        int num;
        for (int i = 0; i < len; i++) {
            num = (int) ((Math.random()) * 10);
            res += num;
        }
        return res;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        SmsCodeEntity smsCodeEntity = smsCache.get(request.getMobileNo());
        if (smsCodeEntity == null) {
            //未发现验证码
            response.setRspCode(IAMResponseCode.VALIDATE_SMS_CODE_FAILURE);
            return response;
        }
        if (!smsCodeEntity.getSmsCode().equals(request.getCode())) {
            //验证码错误
            response.setRspCode(IAMResponseCode.SMS_CODE_ERROR);
            return response;
        }
        //获取用户信息
        List<UserEntity> userEntities = userDAO.selectAnd(UserEntity.builder()
                .mobileNo(request.getMobileNo())
                .build());
        if (userEntities.isEmpty()) {
            //用户不存在
            response.setRspCode(IAMResponseCode.USER_NOT_EXISTS);
            return response;
        }
        UserEntity userEntity = userEntities.get(0);
        if (UserStatus.NORMAL != UserStatus.valueOfCode(userEntity.getUserStatus())) {
            //用户处于异常状态
            response.setRspCode(IAMResponseCode.USER_ABNORMAL_STATE);
            return response;
        }
        String userAndChannel = userEntity.getUserId() + "@" + ChannelType.WEB.getCode();
        String token = sessionCache.getToken(userAndChannel);
        if (token != null) {
            SessionEntity oldSession = sessionCache.getSession(token);
            if (oldSession != null) {
                log.info("user:{} relogin", oldSession.getUserId());
            }
            sessionCache.deleteSession(token);
            sessionCache.deleteToken(userAndChannel);
        }
        List<SelectJobBO> jobs = jobDAO.selectJobByUserId(userEntity.getUserId());
        List<Department> departmentList = new ArrayList();
        Department currentDepartment = null;
        Department firstDepartment = null;
        for (SelectJobBO record : jobs) {
            Department department = Department.builder()
                    .branch(Branch.builder().branchId(record.getBranchId()).branchName(record.getBranchName()).build())
                    .city(City.builder().cityId(record.getCityId()).cityName(record.getCityName()).build())
                    .Job(Job.builder().jobId(record.getJobId()).jobName(record.getJobName()).build())
                    .departmentId(record.getDepartmentId())
                    .departmentName(record.getDepartmentName())
                    .build();
            departmentList.add(department);
            if (firstDepartment == null) {
                firstDepartment = department;
            }
            //如果数据库记录的最后登录部门，则将当前部门设置为最后登录的部门
            if (userEntity.getLatestDepartmentId() != null) {
                if (userEntity.getLatestDepartmentId().compareTo(record.getDepartmentId()) == 0) {
                    currentDepartment = department;
                }
            }
        }
        //如果所有部门都不能匹配，则使用第一个部门为当前部门
        if (currentDepartment == null) {
            currentDepartment = firstDepartment;
        }
        //根据用户号获取所有的部门列表，分支机构信息，岗位信息，角色信息
        //创建会话信息，此信息将存放在缓存服务中，用于验证用户是否登录
        SessionEntity sessionEntity = SessionEntity.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .nickName(userEntity.getNickName())
                .userAvatar(userEntity.getUserAvatar())
                .mobileNo(userEntity.getMobileNo())
                .channel(request.getChannel())
                .token(UUID.randomUUID().toString())
                .currentDepartment(currentDepartment)
                .build();
        sessionEntity.getDepartments().addAll(departmentList);
        sessionCache.createSession(sessionEntity.getToken(), sessionEntity);
        //如果不是APP登录，则在1小时候就超时，否则永久性登录
        if (ChannelType.valueOfCode(request.getChannel()) != ChannelType.APP) {
            sessionCache.expireSession(sessionEntity.getToken(), 10 * 60);
        }
        response = LoginResponse.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .nickName(userEntity.getNickName())
                .userAvatar(userEntity.getUserAvatar())
                .mobileNo(userEntity.getMobileNo())
                .channel(request.getChannel())
                .token(sessionEntity.getToken())
                .currentDepartment(currentDepartment)
                .build();
        response.getDepartments().addAll(departmentList);
        //删除短信验证码
        smsCache.remove(request.getMobileNo());
        return response;
    }

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        LogoutResponse response = new LogoutResponse();
        SessionEntity session = sessionCache.getSession(request.getToken());
        if (session == null) {
            return response;
        }
        List<String> channels = request.getChannels();
        //如果请求没有指定通道，则将所有通道都进行退出
        if (channels.isEmpty()) {
            for (ChannelType value : ChannelType.values()) {
                channels.add(value.getCode());
            }
        }
        //清空所有权限缓存
        authorityCacheHelperService.clear(session.getUserId() + "@*");
        //将需要退出的通道的进行会话销毁
        for (String channel : channels) {
            String userAndChannel = request.getUserId() + "@" + channel;
            String token0 = sessionCache.getToken(userAndChannel);
            if (token0 != null) {
                SessionEntity session0 = sessionCache.getSession(token0);
                if (session0 != null) {
                   log.info("user:{} logout", session0.getUserId());
                }
                sessionCache.deleteSession(session0.getToken());
                sessionCache.deleteToken(userAndChannel);
            }
        }
        sessionCache.deleteSession(session.getToken());
        return response;
    }

    @Override
    public FetchApplicationResponse fetchApplication(FetchApplicationRequest request) {
        FetchApplicationResponse response = new FetchApplicationResponse();
        List<ApplicationEntity> applicationEntities = applicationDAO.selectAnd(ApplicationEntity.builder()
                        .appStatus(AppStatus.ENABLED.getCode())
                        .build()
        );
        for (ApplicationEntity applicationEntity : applicationEntities) {
            response.getRecords().add(ApplicationInfo.builder()
                    .appCode(applicationEntity.getAppCode())
                    .appIcon(applicationEntity.getAppIcon())
                    .appTitle(applicationEntity.getAppTitle())
                    .appUrl(applicationEntity.getAppUrl())
                    .owner(true)
                    .build());
        }
        return response;
    }

    @Override
    public FetchConfigResponse fetchConfig(FetchConfigRequest request) {
        FetchConfigResponse response = new FetchConfigResponse();
        response.getRecords().add(FetchConfigRecord.builder().name(IAMConfigKey.IAM_BASE_URL).value(iamConfig.getIamBaseUrl()).build());
        response.getRecords().add(FetchConfigRecord.builder().name(IAMConfigKey.IAM_DEBUG).value(Boolean.toString(iamConfig.isDebug())).build());
        response.getRecords().add(FetchConfigRecord.builder().name(IAMConfigKey.IAM_COOKIE_NAME).value(iamConfig.getCookieName()).build());
        response.getRecords().add(FetchConfigRecord.builder().name(IAMConfigKey.IAM_COOKIE_PASSWORD).value(iamConfig.getCookiePassword()).build());
        response.getRecords().add(FetchConfigRecord.builder().name(IAMConfigKey.IAM_DOMAIN).value(iamConfig.getDomain()).build());
        response.getRecords().add(FetchConfigRecord.builder().name(IAMConfigKey.IAM_CHANGE_DEPARTMENT).value("/changeDepartment").build());
        response.getRecords().add(FetchConfigRecord.builder().name(IAMConfigKey.IAM_ZOOKEEPER).value("zookeeper://localhost:2181").build());
        return response;
    }

    @Override
    public TestAuthorizeUserResponse testAuthorizeUser(TestAuthorizeUserRequest request) {
        TestAuthorizeUserResponse response = new TestAuthorizeUserResponse();
        String newToken = userUuidCache.getUuid(request.getUuid());
        if (newToken == null || !"wait".equals(newToken)) {
            response.setRspCode(IAMResponseCode.QRCODE_INVALIDATION);
            return response;
        }
        //通过一登陆的令牌获取会会话
        SessionEntity oldSession = sessionCache.getSession(request.getToken());
        if (oldSession == null) {
            response.setRspCode(IAMResponseCode.SESSION_INVALIDATION);
            return response;
        }
        //获取用户信息
        UserEntity userEntity = userDAO.selectByPrimaryKey(oldSession.getUserId());
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
        return response;
    }

    @Override
    public AuthorizeUserResponse authorizeUser(AuthorizeUserRequest request) {
        AuthorizeUserResponse response = new AuthorizeUserResponse();
        String newToken = userUuidCache.getUuid(request.getUuid());
        //如果uuid对应的token值为空，或者不为wait
        if (newToken == null || !"wait".equals(newToken)) {
            response.setRspCode(IAMResponseCode.QRCODE_INVALIDATION);
            return response;
        }
        //通过一登陆的令牌获取会会话
        SessionEntity oldSession = sessionCache.getSession(request.getToken());
        if (oldSession == null) {
            response.setRspCode(IAMResponseCode.SESSION_INVALIDATION);
            return response;
        }
        //获取用户信息
        UserEntity userEntity = userDAO.selectByPrimaryKey(oldSession.getUserId());
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
        //生成新的TOKEN 值
        newToken = UUID.randomUUID().toString();
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
        //创建新会话信息，此信息将存放在缓存服务中，用于验证用户是否登录
        SessionEntity sessionEntity = SessionEntity.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .nickName(userEntity.getNickName())
                .userAvatar(userEntity.getUserAvatar())
                .mobileNo(userEntity.getMobileNo())
                .channel(request.getChannel())
                .token(newToken)
                .currentDepartment(currentDepartment)
                .build();
        sessionEntity.getDepartments().addAll(departmentList);
        sessionCache.createSession(sessionEntity.getToken(), sessionEntity);
        sessionCache.expireSession(sessionEntity.getToken(), 10 * 60);
        //创建用户渠道和token之间的关系
        sessionCache.createToken(userEntity.getUserId() + "@" + request.getChannel(), newToken);
        userUuidCache.createUuid(request.getUuid(), newToken);
        return response;
    }

    @Override
    public ChangeDepartmentResponse changeDepartment(ChangeDepartmentRequest request) {
        ChangeDepartmentResponse response = new ChangeDepartmentResponse();
        SessionEntity session = sessionCache.getSession(request.getToken());
        //如果是没登录
        if (session == null) {
            response.setRspCode(IAMResponseCode.SESSION_INVALIDATION);
            return response;
        }
        Integer departmentId = request.getDepartmentId();
        Department currentDepartment = null;
        List<Department> departments = session.getDepartments();
        boolean found = false;
        for (Department department : departments) {
            if (department.getDepartmentId().compareTo(departmentId) == 0) {
                currentDepartment = department;
                found = true;
            }
        }
        if (!found) {
            response.setRspCode(IAMResponseCode.DEPARTMENT_NOT_EXISTS);
            return response;
        }
        sessionCache.expireSession(request.getToken(), 10 * 60);
        userDAO.updateByPrimaryKeySelective(UserEntity.builder()
                .userId(session.getUserId())
                .latestDepartmentId(departmentId)
                .build());
        response = ChangeDepartmentResponse.builder()
                .userId(session.getUserId())
                .userName(session.getUserName())
                .nickName(session.getNickName())
                .userAvatar(session.getUserAvatar())
                .mobileNo(session.getMobileNo())
                .channel(session.getChannel())
                .token(session.getToken())
                .currentDepartment(currentDepartment)
                .build();
        response.getDepartments().addAll(departments);
        return response;
    }

    @Override
    public GetUserResponse getUser(GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        UserEntity userEntity = userDAO.selectByPrimaryKey(request.getUserId());
        if (userEntity == null) {
            response.setRspCode(IAMResponseCode.USER_NOT_EXISTS);
            return response;
        }
        response.setUserId(userEntity.getUserId());
        response.setUserName(userEntity.getUserName());
        response.setNickName(userEntity.getNickName());
        response.setMobileNo(userEntity.getMobileNo());
        response.setUserAvatar(userEntity.getUserAvatar());
        response.setUserStatus(userEntity.getUserStatus());
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
        response.setCurrentDepartment(currentDepartment);
        return response;
    }

    @Override
    public FetchUsersResponse fetchUsers(FetchUsersRequest request) {
        FetchUsersResponse response = new FetchUsersResponse();
        List<UserEntity> userEntities = userDAO.selectAnd(UserEntity.builder().userStatus(request.getUserStatus()).build());
        for (UserEntity record : userEntities){
            response.addNode(Node.builder().text(record.getUserName()).value(record.getUserId()).build());
        }
        return response;
    }
}

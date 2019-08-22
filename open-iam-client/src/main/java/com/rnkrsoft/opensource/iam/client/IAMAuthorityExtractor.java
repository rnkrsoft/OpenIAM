package com.rnkrsoft.opensource.iam.client;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.rnkrsoft.config.ConfigProvider;
import com.rnkrsoft.opensource.iam.IAMConfigKey;
import com.rnkrsoft.opensource.iam.cookie.Cookies;
import com.rnkrsoft.opensource.iam.domains.FetchConfigRequest;
import com.rnkrsoft.opensource.iam.domains.FetchConfigResponse;
import com.rnkrsoft.opensource.iam.domains.ValidateLoginRequest;
import com.rnkrsoft.opensource.iam.domains.ValidateLoginResponse;
import com.rnkrsoft.opensource.iam.services.IAMService;
import com.rnkrsoft.opensource.iam.cookie.LoginToken;
import com.rnkrsoft.opensource.iam.vo.Branch;
import com.rnkrsoft.opensource.iam.vo.City;
import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.skeleton4j.authority.DefaultAuthorityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.web.skeleton4j.Version;
import javax.web.skeleton4j.authority.AuthorityContext;
import javax.web.skeleton4j.boot.Skeleton4jApplicationLoader;
import javax.web.skeleton4j.config.Skeleton4jConfig;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 * IAM权限提取器
 * 提取Cookie中存放的令牌信息
 */
@Slf4j
public class IAMAuthorityExtractor implements javax.web.skeleton4j.authority.AuthorityExtractor {
    ReferenceConfig<IAMService> referenceConfig;
    long lastFetchConfigMs = System.currentTimeMillis();
    long configTimeoutSeconds = 60;
    FetchConfigResponse configCache;

    public IAMAuthorityExtractor() {
        ConfigProvider configProvider = Skeleton4jApplicationLoader.getConfigProvider();
        ApplicationConfig config = new ApplicationConfig(configProvider.getString(Skeleton4jConfig.APP, "IAM-CLIENT"));
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(configProvider.getString(IAMConfigKey.IAM_ZOOKEEPER, configProvider.getString(Skeleton4jConfig.ZOOKEEPER_ADDRESS)));
        registryConfig.setProtocol("zookeeper");
        registryConfig.setTimeout(30 * 1000);

        referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(config);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setInterface(IAMService.class.getName());
        referenceConfig.setGeneric(false);
        referenceConfig.setTimeout(30 * 1000);
        //引用配置缓存对象

    }

    IAMService getIamService() {
        return ReferenceConfigCache.getCache().get(referenceConfig);
    }

    void fetchConfig() {
        if (this.configCache != null && System.currentTimeMillis() - lastFetchConfigMs < configTimeoutSeconds * 1000) {
            return;
        }
        FetchConfigResponse fetchConfigResponse = getIamService().fetchConfig(new FetchConfigRequest());
        if (fetchConfigResponse.success()) {
            this.configCache = fetchConfigResponse;
            lastFetchConfigMs = System.currentTimeMillis();
        } else {
            log.error("获取IAM配置发生失败,{}", fetchConfigResponse);
        }
    }

    @Override
    public AuthorityContext extract(HttpServletRequest request, HttpServletResponse response) {
        AuthorityContext ctx = null;
        try {
            ctx = extract0(request, response);
        } catch (Exception e) {
            log.error("提取用户信息发生错误", e);
            ctx = new DefaultAuthorityContext();
        }
        return ctx;
    }

    AuthorityContext extract0(HttpServletRequest request, HttpServletResponse response) {
        DefaultAuthorityContext ctx = new DefaultAuthorityContext();
        String domain = request.getServerName();
        String url = request.getScheme() + "://" + domain + ":" + request.getServerPort();
        log.debug("url:{}", url);
        try {
            fetchConfig();
        } catch (Exception e) {
            log.error("调用远程配置发生错误", e);
            return setFailure(ctx, request, url);
        }
        LoginToken loginToken = Cookies.load(request, configCache.getConfig(IAMConfigKey.IAM_COOKIE_PASSWORD, String.class), configCache.getConfig(IAMConfigKey.IAM_COOKIE_NAME, String.class));
        if (loginToken == null) {
            log.error("提取登录令牌信息失败,跳转到登录页面");
            return setFailure(ctx, request, url);
        }

        if (!checkToken(loginToken.getToken(), loginToken.getUserId())) {
            log.error("校验登录令牌信息失败,跳转到登录页面");
            return setFailure(ctx, request, url);
        }
        Department department = loginToken.getCurrentDepartment();
        if (department != null) {
            ctx.setUserDepartmentId(department.getDepartmentId() + "");
            ctx.setUserDepartmentName(department.getDepartmentName());
            if (department.getBranch() != null) {
                Branch branch = department.getBranch();
                ctx.setUserBranchId(branch.getBranchId() + "");
                ctx.setUserBranchName(branch.getBranchName());
            }
            if (department.getCity() != null) {
                City city = department.getCity();
                ctx.setUserCityId(city.getCityId() + "");
                ctx.setUserCityName(city.getCityName());
            }
        }
        ctx.setUserId(loginToken.getUserId());
        ctx.setUserName(loginToken.getUserName());
        ctx.setUserAvatar(loginToken.getUserAvatar());
        ctx.setNickName(loginToken.getNickName());
        ctx.setUserMobile(loginToken.getMobileNo());
        ctx.setToken(loginToken.getToken());
        ctx.setLastLoginTime(loginToken.getLatestLoginTime() != null ? new Date(loginToken.getLatestLoginTime()) : null);
        ctx.setLastActiveTime(loginToken.getActiveTime() != null ? new Date(loginToken.getActiveTime()) : null);
        ctx.setBaseUrl(getBaseUrl());
        ctx.setNeedCheckToken(true);
        ctx.setRelogin(false);
        ctx.setFailure(false);

        try {
            String urlEncode = URLEncoder.encode(url, "UTF8");
            List<String> departments = ownerDepartmentsHtml(loginToken.getDepartments(), urlEncode);
            ctx.setLoginUrl(getBaseUrl() + "?referrer=" + urlEncode);
            String version = Version.version();
            if ("1.0.2".equals(version)) {
                ctx.setValue("DEPARTMENTS_HTML", StringUtils.collectionToDelimitedString(departments, ""));
            } else if ("1.0.3".equals(version)) {
                ctx.getDepartmentsHtmlCode().addAll(departments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctx;
    }

    String getBaseUrl() {
        return configCache.getConfig(IAMConfigKey.IAM_BASE_URL, String.class);
    }

    AuthorityContext setFailure(AuthorityContext ctx, HttpServletRequest request, String url) {
        String urlEncode = "";
        try {
            urlEncode = URLEncoder.encode(url, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ctx.setLoginUrl(getBaseUrl() + "?referrer=" + urlEncode);
        ctx.setRelogin(true);
        ctx.setFailure(true);
        return ctx;
    }

    /**
     * 用户可切换的城市
     *
     * @return
     */
    List<String> ownerDepartmentsHtml(List<Department> departments, String url) {
        List<String> changeUrlList = new ArrayList<String>();
        for (Department department : departments) {
            String html = "<li><a href=\"" + getBaseUrl() + configCache.getConfig(IAMConfigKey.IAM_CHANGE_DEPARTMENT, String.class) + "?departmentId=" + department.getDepartmentId() + "&referrer=" + url + "\" target=\"_self\">" + department.getDepartmentName() + "·" + department.getBranch().getBranchName() + "(" + department.getCity().getCityName() + ")</a></li>";
            changeUrlList.add(html);
        }
        return changeUrlList;
    }

    /**
     * 验证token
     *
     * @return
     */
    boolean checkToken(String token, String userId) {
        IAMService iamService = getIamService();
        ValidateLoginRequest validateLoginRequest = ValidateLoginRequest
                .builder()
                .token(token)
                .userId(userId).build();
        ValidateLoginResponse validateLoginResponse = iamService.validateLogin(validateLoginRequest);
        if (validateLoginResponse.success()) {
            if (log.isDebugEnabled()) {
                log.debug("check user:{} token:{} success!", userId, token);
            }
            return true;
        } else {
            log.error("check user:{} token:{} failure! cause:{}", userId, token, validateLoginResponse);
            return false;
        }
    }
}

package com.rnkrsoft.opensource.iam.client;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.rnkrsoft.config.ConfigProvider;
import com.rnkrsoft.opensource.iam.IAMConfigKey;
import com.rnkrsoft.opensource.iam.domains.*;
import com.rnkrsoft.opensource.iam.enums.YesOrNo;
import com.rnkrsoft.opensource.iam.services.IAMService;
import com.rnkrsoft.reflection4j.extension.Extension;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.web.skeleton4j.authority.AuthorityContext;
import javax.web.skeleton4j.boot.Skeleton4jApplicationLoader;
import javax.web.skeleton4j.config.Skeleton4jConfig;
import java.util.*;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 * IAM权限服务
 */
@Slf4j
@Extension("iam")
public class IAMAuthorityService implements javax.web.skeleton4j.authority.AuthorityService {
    ReferenceConfig<IAMService> referenceConfig;
    //全局共享单例，当多个用户在访问时，可能导致发生脏读
    long lastFetchConfigMs = System.currentTimeMillis();
    long configTimeoutSeconds = 60;
    FetchConfigResponse configCache;

    public IAMAuthorityService() {
        ConfigProvider configProvider = Skeleton4jApplicationLoader.getConfigProvider();
        ApplicationConfig config = new ApplicationConfig(configProvider.getString(Skeleton4jConfig.APP, "IAM-CLIENT"));
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(configProvider.getString(Skeleton4jConfig.ZOOKEEPER_ADDRESS, "zookeeper://localhost:2181"));
        registryConfig.setProtocol("zookeeper");
        registryConfig.setTimeout(30 * 1000);

        referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(config);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setInterface(IAMService.class);
        referenceConfig.setGeneric(false);
        referenceConfig.setTimeout(30 * 1000);
        referenceConfig.setCache("false");

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
        }
    }

    @Override
    public void initialize() {

    }

    @Override
    public void destroy() {
    }

    @Override
    public boolean authority(AuthorityContext context) {
        try {
            return authority0(context);
        } catch (Exception e) {
            log.error("验证权限发生错误", e);
            return false;
        }
    }

    boolean authority0(AuthorityContext context) {
        try {
            fetchConfig();
        } catch (Exception e) {
            log.error("调用远程配置发生错误", e);
            return false;
        }
        if (configCache.getConfig(IAMConfigKey.IAM_DEBUG, Boolean.class)) {
            log.warn("系统处于调试模式，不进行权限控制");
            return true;
        }
        String sessionId = UUID.randomUUID().toString();
        MDC.put("sessionId", sessionId);
        log.debug("begin get authorities");
        Map<String, AuthorityRecord> recordMap = null;
        //说明没有权限
        try {
            FetchAuthorityResponse fetchAuthorityResponse = getIamService().fetchAuthority(FetchAuthorityRequest.builder()
                            .sessionId(sessionId)
                            .appCode(context.getApp())
                            .userId(context.getUserId())
                            .token(context.getToken())
                            .build()
            );
            if (fetchAuthorityResponse == null || !fetchAuthorityResponse.success()) {
                log.error("获取系统权限失败，{}", fetchAuthorityResponse);
                return false;
            }
            recordMap = new HashMap();
            //在同一个接口或者页面鉴权中，将调用的信息缓存起来
            for (AuthorityRecord record : fetchAuthorityResponse.getAuthorities()) {
                recordMap.put(record.getPath(), record);
                if (log.isDebugEnabled()) {
                    log.debug("path:{}  {}/{}/{}", record.getPath(), record.getAppName(), record.getProductName(), record.getActionName(), record.getSubActionName());
                }
            }
        } catch (Exception e) {
            //防止在DUBBO调不通的时候，权限验证错误通过
            log.error("获取dubbo服务发生错误", e);
            return false;
        }
        String path = context.getApp() + "/" + context.getProduct() + "/" + context.getAction() + "/" + context.getSubAction();
        AuthorityRecord authorityRecord = recordMap.get(path);
        if (log.isDebugEnabled()) {
            log.debug("path:{} authority:{}", authorityRecord);
        }
        return authorityRecord != null;
    }
}

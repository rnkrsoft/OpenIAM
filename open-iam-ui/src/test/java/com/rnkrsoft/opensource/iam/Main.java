package com.rnkrsoft.opensource.iam;

import com.rnkrsoft.skeleton4j.authority.mock.MockAuthorityExtractor;
import com.rnkrsoft.skeleton4j.authority.mock.MockAuthorityService;
import com.rnkrsoft.skeleton4j.pool.CommunityModulePool;
import com.rnkrsoft.skeleton4j.registry.CommunityComponentRegistry;
import com.rnkrsoft.skeleton4j.service.CommunitySkeleton4jService;
import com.rnkrsoft.skeleton4j.theme.layui.community.LayuiCommunityTheme;
import com.rnkrsoft.skeleton4j.validate.DefaultSkeleton4jValidator;

import javax.web.skeleton4j.boot.Skeleton4jApplicationLoader;
import javax.web.skeleton4j.boot.annotation.Skeleton4jApplication;
import javax.web.skeleton4j.enums.RuntimeEnvironment;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Skeleton4jApplication(
        app = "OpenIAM",
        appDesc = "OpenIAM",
        themes = LayuiCommunityTheme.class,
        defaultTheme = LayuiCommunityTheme.class,
        env = RuntimeEnvironment.DEV,
        modules = {
                IAM.class
        },
        skeleton4jService = CommunitySkeleton4jService.class,
        serverPort = 8082,
        authorityExtractor = MockAuthorityExtractor.class,
        authorityService = MockAuthorityService.class,
//        authorityExtractor = IAMAuthorityExtractor.class,
//        authorityService = IAMAuthorityService.class,
        modulePool = CommunityModulePool.class,
        componentRegistry = CommunityComponentRegistry.class,
        validator = DefaultSkeleton4jValidator.class,
        zookeeperAddress = "zookeeper://localhost:2181",
        logoutUrl = "http://localhost/logout"
)
public class Main {

    public static void main(String[] args) throws Exception {
        Skeleton4jApplicationLoader.runWith(Main.class, args);
    }
}
package com.rnkrsoft.opensource.iam;

import com.rnkrsoft.skeleton4j.StandardSkeleton4jService;
import com.rnkrsoft.skeleton4j.authority.mock.MockAuthorityExtractor;
import com.rnkrsoft.skeleton4j.authority.mock.MockAuthorityService;
import com.rnkrsoft.skeleton4j.pool.SimpleModulePool;
import com.rnkrsoft.skeleton4j.theme.layui.LayuiTheme;

import javax.web.skeleton4j.boot.Skeleton4jApplicationLoader;
import javax.web.skeleton4j.boot.annotation.Skeleton4jApplication;
import javax.web.skeleton4j.enums.RuntimeEnvironment;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Skeleton4jApplication(
        app = "OpenIAM",
        appDesc = "OpenIAM",
        themes = LayuiTheme.class,
        defaultTheme = LayuiTheme.class,
        env = RuntimeEnvironment.DEV,
        modules = {
                IAM.class
        },
        skeleton4jService = StandardSkeleton4jService.class,
        serverPort = 8082,
        authorityExtractor = MockAuthorityExtractor.class,
        authorityService = MockAuthorityService.class,
//        authorityExtractor = IAMAuthorityExtractor.class,
//        authorityService = IAMAuthorityService.class,
        modulePool = SimpleModulePool.class,
        zookeeperAddress = "zookeeper://localhost:2181",
        logoutUrl = "http://localhost/logout"
)
public class Main {

    public static void main(String[] args) throws Exception {
        Skeleton4jApplicationLoader.runWith(Main.class, args);
    }
}
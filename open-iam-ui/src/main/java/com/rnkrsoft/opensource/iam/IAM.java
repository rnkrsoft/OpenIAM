package com.rnkrsoft.opensource.iam;

import javax.web.skeleton4j.annotation.Skeleton4jModule;
import javax.web.skeleton4j.enums.InterfaceCall;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Skeleton4jModule(
        name = "IAM",
        desc = "开源身份识别与访问管理",
        interfacePackages = {
                "com.rnkrsoft.opensource.iam.skeleton4j.interfaces"
        },
        pagePackages = {
                "com.rnkrsoft.opensource.iam.skeleton4j.pages"
        },
        call = InterfaceCall.SPRING_BEAN
)
public interface IAM {
}

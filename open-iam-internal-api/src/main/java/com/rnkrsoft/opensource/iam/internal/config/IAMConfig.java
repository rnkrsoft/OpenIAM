package com.rnkrsoft.opensource.iam.internal.config;

import com.rnkrsoft.framework.config.annotation.DynamicConfig;
import com.rnkrsoft.framework.config.annotation.DynamicParam;
import lombok.Data;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@DynamicConfig
public class IAMConfig {
    @DynamicParam(value = "${iam.domain}", desc = "域名")
    String domain;
    @DynamicParam(value = "${iam.iamBaseUrl}", desc = "IAM基本地址")
    String iamBaseUrl;
    @DynamicParam(value = "${iam.cookieName}", desc = "COOKIE名，用于存放令牌信息")
    String cookieName;
    @DynamicParam(value = "${iam.cookiePassword}", desc = "加密Cookie的棉麻")
    String cookiePassword;
    @DynamicParam(value = "${iam.mobileUrl}", desc = "移动地址")
    String mobileUrl;
    @DynamicParam(value = "${iam.logo}", desc = "系统图标")
    String logo;
    @DynamicParam(value = "${iam.copyright}", desc = "版权申明信息")
    String copyright;
    @DynamicParam(value = "${iam.title}", desc = "系统名称")
    String title;
    @DynamicParam(value = "${iam.desc}", desc = "公司简介")
    String desc;
    @DynamicParam(value = "${iam.default.loginType}", desc = "系统名称")
    String defaultLoginType;
    @DynamicParam(value = "${iam.debug}", desc = "调试模式，调试模式不验证短信验证码")
    boolean debug;
    @DynamicParam(value = "${iam.useAuthorityCache}", desc = "是否使用权限缓存")
    boolean useAuthorityCache;
}

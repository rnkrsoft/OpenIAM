package com.rnkrsoft.opensource.iam.gateway.login.facade;

import com.rnkrsoft.opensource.iam.gateway.login.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@ApidocService(value = "登录服务", channel = "iam")
public interface LoginFacade {
    @ApidocInterface(value = "短信登录", name = "101", version = "1", usage = "用于短信登录APP渠道")
    SmsLoginResponse smsLogin(SmsLoginRequest request);


    @ApidocInterface(value = "检测随即令牌是否有效", name = "102", version = "1", usage = "用于对指定通道的随机令牌进行检测")
    CheckUuidResponse checkUuid(CheckUuidRequest request);

    @ApidocInterface(value = "授权绑定登录", name = "103", version = "1", usage = "用于对指定通道授权登录")
    BindLoginResponse bindLogin(BindLoginRequest request);


}

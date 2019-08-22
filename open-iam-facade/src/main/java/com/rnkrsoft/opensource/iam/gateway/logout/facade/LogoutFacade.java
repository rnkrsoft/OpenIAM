package com.rnkrsoft.opensource.iam.gateway.logout.facade;

import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutByChannelRequest;
import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutByChannelResponse;
import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutResponse;
import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutRequest;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by woate on 2019/7/14.
 */
@ApidocService(value = "注销服务", channel = "iam")
public interface LogoutFacade {
    @ApidocInterface(value = "注销指定渠道", name = "104", version = "1", usage = "按照登录用户注销指定渠道")
    LogoutByChannelResponse logoutByChannel(LogoutByChannelRequest request);
    @ApidocInterface(value = "注销所有渠道", name = "105", version = "1", usage = "按照登录用户的所有渠道进行注销")
    LogoutResponse logout(LogoutRequest request);
}

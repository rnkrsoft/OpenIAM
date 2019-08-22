package com.rnkrsoft.opensource.iam.gateway.sms.facade;

import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendLoginSmsRequest;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendLoginSmsResponse;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendNoticeSmsRequest;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendNoticeSmsResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@ApidocService(value = "短信服务", channel = "iam")
public interface SmsFacade {
    @ApidocInterface(value = "发送登录短信", name = "301", version = "1")
    SendLoginSmsResponse sendLoginSms(SendLoginSmsRequest request);
    @ApidocInterface(value = "发送通知短信", name = "302", version = "1")
    SendNoticeSmsResponse sendNoticeSms(SendNoticeSmsRequest request);
}

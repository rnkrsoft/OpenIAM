package com.rnkrsoft.opensource.iam.gateway.sms.domains;

import lombok.Data;
import lombok.ToString;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
@ToString(callSuper = true)
public class SendLoginSmsResponse extends AbstractResponse{
    @ApidocElement("短信验证码")
    String smsCode;
}

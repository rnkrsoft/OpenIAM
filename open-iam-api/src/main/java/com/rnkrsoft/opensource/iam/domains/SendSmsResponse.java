package com.rnkrsoft.opensource.iam.domains;

import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
public class SendSmsResponse extends AbstractResponse{
    @ApidocElement("短信验证码")
    String smsCode;
}

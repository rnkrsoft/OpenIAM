package com.rnkrsoft.opensource.iam.gateway.sms.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class SendNoticeSmsRequest implements Serializable{
    @ApidocElement(value = "手机号码", minLen = 11, maxLen = 11)
    String mobileNo;
    @ApidocElement(value = "短信内容", minLen = 1, maxLen = 128)
    String content;
}

package com.rnkrsoft.opensource.iam.domains;

import lombok.Data;
import lombok.ToString;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;

@Data
@ToString(callSuper = true)
public class ValidateLoginResponse extends AbstractResponse {
    @ApidocElement("用户标识")
    String userId;
    @ApidocElement("用户名")
    String userName;
    @ApidocElement("通道")
    String channel;
}
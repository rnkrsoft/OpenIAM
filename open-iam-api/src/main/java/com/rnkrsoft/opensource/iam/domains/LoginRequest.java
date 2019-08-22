package com.rnkrsoft.opensource.iam.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable{
    @ApidocElement("手机号")
    String mobileNo;
    @ApidocElement("短信验证码")
    String code;
    @ApidocElement("渠道")
    String channel;
}

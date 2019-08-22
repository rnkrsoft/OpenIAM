package com.rnkrsoft.opensource.iam.cache.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SmsCodeEntity implements Serializable {
    /**
     * 手机号
     */
    String mobileNo;
    /**
     * 短信验证码
     */
    String smsCode;
}

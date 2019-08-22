package com.rnkrsoft.opensource.iam.internal.config;

import com.rnkrsoft.framework.config.annotation.DynamicConfig;
import com.rnkrsoft.framework.config.annotation.DynamicParam;
import lombok.Data;

/**
 * Created by woate on 2019/7/17.
 */
@Data
@DynamicConfig
public class SmsConfig {
    @DynamicParam(value = "${com.aliyun.sms.accessKeyId}", desc = "阿里云短信key")
    String aliyunAccessKeyId;
    @DynamicParam(value = "${com.aliyun.sms.accessKeySecret}", desc = "阿里云短信Secret")
    String aliyunAccessKeySecret;
    @DynamicParam(value = "${com.aliyun.sms.validateSms.templateCode}", desc = "阿里云验证码短信模板")
    String validateSmsTemplateCode;
    @DynamicParam(value = "${com.aliyun.sms.noticeSms.templateCode}", desc = "阿里云通知短信模板")
    String noticeSmsTemplateCode;
    @DynamicParam(value = "${com.aliyun.sms.aliyunSignName}", desc = "案例云签字名称")
    String aliyunSignName;
}

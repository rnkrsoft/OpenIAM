package com.rnkrsoft.opensource.iam.internal.sms.services;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
public interface SmsService {
    /**
     * 发送短信
     * @param mobileNo 手机号
     * @param code 验证码
     */
    void sendValidateMessage(String mobileNo, String code);

    /**
     * 发送通知短信
     * @param mobileNo 手机号
     * @param message 短消息
     */
    void sendNoticeMessage(String mobileNo, String message);
}

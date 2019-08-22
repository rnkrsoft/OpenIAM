package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.opensource.iam.internal.sms.services.SmsService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Slf4j
public class MockSmsService implements SmsService{
    @Override
    public void sendValidateMessage(String mobileNo, String code) {
        log.info("正在发送短信【{}】到手机‘{}’", code,  mobileNo);
    }

    @Override
    public void sendNoticeMessage(String mobileNo, String message) {
        log.info("正在发送短信【{}】到手机‘{}’", message,  mobileNo);
    }
}

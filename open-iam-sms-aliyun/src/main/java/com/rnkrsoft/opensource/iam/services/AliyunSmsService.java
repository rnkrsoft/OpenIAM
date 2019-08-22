package com.rnkrsoft.opensource.iam.services;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rnkrsoft.opensource.iam.internal.config.SmsConfig;
import com.rnkrsoft.opensource.iam.internal.sms.services.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by woate on 2019/7/17.
 */
@Slf4j
public class AliyunSmsService implements SmsService {
    @Autowired
    SmsConfig smsConfig;
    final static Gson GSON = new GsonBuilder().serializeNulls().create();

    @Override
    public void sendValidateMessage(String mobileNo, String code) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAliyunAccessKeyId(), smsConfig.getAliyunAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(mobileNo);
        sendSmsRequest.setSignName(smsConfig.getAliyunSignName());
        sendSmsRequest.setTemplateCode(smsConfig.getValidateSmsTemplateCode());
        Map params = new HashMap();
        params.put("code", code);
        sendSmsRequest.setTemplateParam(GSON.toJson(params));

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        log.info("手机号:{} 发送验证码结果{}:{}", mobileNo, sendSmsResponse.getCode(), sendSmsResponse.getMessage());
    }

    @Override
    public void sendNoticeMessage(String mobileNo, String message) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAliyunAccessKeyId(), smsConfig.getAliyunAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(mobileNo);
        sendSmsRequest.setSignName(smsConfig.getAliyunSignName());
        sendSmsRequest.setTemplateCode(smsConfig.getNoticeSmsTemplateCode());
        Map params = new HashMap();
        params.put("message", message);
        sendSmsRequest.setTemplateParam(GSON.toJson(params));

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        log.info("手机号:{} 发送通知结果{}:{}", mobileNo, sendSmsResponse.getCode(), sendSmsResponse.getMessage());
    }
}

package com.rnkrsoft.opensource.iam.gateway.facades;

import com.rnkrsoft.opensource.iam.domains.SendSmsRequest;
import com.rnkrsoft.opensource.iam.domains.SendSmsResponse;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendLoginSmsRequest;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendLoginSmsResponse;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendNoticeSmsRequest;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendNoticeSmsResponse;
import com.rnkrsoft.opensource.iam.gateway.sms.facade.SmsFacade;
import com.rnkrsoft.opensource.iam.services.IAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Service
public class SmsFacadeImpl implements SmsFacade {
    @Autowired
    IAMService iamService;

    @Override
    public SendLoginSmsResponse sendLoginSms(SendLoginSmsRequest request) {
        SendLoginSmsResponse response = new SendLoginSmsResponse();
        SendSmsResponse sendSmsResponse = iamService.send(SendSmsRequest.builder()
                        .mobileNo(request.getMobileNo())
                        .build()
        );
        if (!sendSmsResponse.success()) {
            response.setRspCode(sendSmsResponse.getRspCode());
            response.setRspDesc(sendSmsResponse.getRspDesc());
            response.setSmsCode(sendSmsResponse.getSmsCode());
            return response;
        }
        return response;
    }

    @Override
    public SendNoticeSmsResponse sendNoticeSms(SendNoticeSmsRequest request) {
        return null;
    }
}

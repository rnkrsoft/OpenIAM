package com.rnkrsoft.opensource.iam.gateway.facades;

import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutByChannelRequest;
import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutByChannelResponse;
import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutRequest;
import com.rnkrsoft.opensource.iam.gateway.logout.domains.LogoutResponse;
import com.rnkrsoft.opensource.iam.gateway.logout.facade.LogoutFacade;
import com.rnkrsoft.opensource.iam.services.IAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by woate on 2019/7/14.
 */
@Service
public class LogoutFacadeImpl implements LogoutFacade {
    @Autowired
    IAMService iamService;
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        LogoutResponse response = new LogoutResponse();
        com.rnkrsoft.opensource.iam.domains.LogoutRequest logoutRequest = com.rnkrsoft.opensource.iam.domains.LogoutRequest.builder()
                .token(request.getToken())
                .userId(request.getUserId())
                .build();
        com.rnkrsoft.opensource.iam.domains.LogoutResponse logoutResponse = iamService.logout(logoutRequest);
        if (!logoutResponse.success()){
            response.setRspCode(logoutResponse.getRspCode());
            response.setRspDesc(logoutResponse.getRspDesc());
            return response;
        }
        return response;
    }

    @Override
    public LogoutByChannelResponse logoutByChannel(LogoutByChannelRequest request) {
        return null;
    }
}

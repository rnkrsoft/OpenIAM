package com.rnkrsoft.opensource.iam.gateway.services;

import com.rnkrsoft.opensource.iam.cache.dao.SessionCache;
import com.rnkrsoft.opensource.iam.cache.entity.SessionEntity;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.platform.domains.*;
import com.rnkrsoft.platform.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by woate on 2019/7/12.
 */
public class IAMTokenServiceImpl implements TokenService {
    @Autowired
    SessionCache sessionCache;
    @Override
    public CreateTokenResponse createToken(CreateTokenRequest createTokenRequest) {
        throw new RuntimeException("不支持该方法");
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        throw new RuntimeException("不支持该方法");
    }

    @Override
    public ValidateTokenResponse validateToken(ValidateTokenRequest request) {
        ValidateTokenResponse response = new ValidateTokenResponse();
        SessionEntity session = sessionCache.getSession(request.getToken());
        if (session == null){
            response.setRspCode(IAMResponseCode.FAIL);
            return response;
        }
        response.setChannel(session.getChannel());
        response.setUserId(session.getUserId());
        response.setUserName(session.getUserName());
        return response;
    }

    @Override
    public RemoveTokenResponse removeToken(RemoveTokenRequest removeTokenRequest) {
       throw new RuntimeException("不支持该方法");
    }
}

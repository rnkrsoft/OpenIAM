package com.rnkrsoft.opensource.iam.web.controller;

import com.rnkrsoft.opensource.iam.internal.config.IAMConfig;
import com.rnkrsoft.opensource.iam.domains.*;
import com.rnkrsoft.opensource.iam.services.IAMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Slf4j
@RequestMapping("/debug")
public class DebugController {
    @Autowired
    IAMConfig iamConfig;
    @Autowired
    IAMService iamService;

    @RequestMapping("/authorizeUser")
    @ResponseBody
    public Object authorizeUser(String token,
                         String uuid,
                         String channel,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        if (!iamConfig.isDebug()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        AuthorizeUserResponse authorizeUserResponse = iamService.authorizeUser(AuthorizeUserRequest.builder()
                        .token(token)
                        .uuid(uuid)
                        .channel(channel)
                        .build()
        );
        log.debug("bind user,  code:{}({})", authorizeUserResponse.getRspCode(), authorizeUserResponse.getRspDesc());
        return authorizeUserResponse;
    }

    @RequestMapping("/sendSms")
    @ResponseBody
    public Object sendSms(String mobileNo,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        if (!iamConfig.isDebug()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        SendSmsResponse sendSmsResponse = iamService.send(SendSmsRequest.builder()
                        .mobileNo(mobileNo)
                        .build()
        );
        log.debug("send sms {} : {},  code:{}({})", mobileNo, sendSmsResponse.getSmsCode(), sendSmsResponse.getRspCode(), sendSmsResponse.getRspDesc());
        return sendSmsResponse;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(String mobileNo,
                      String channel,
                      String code,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        if (!iamConfig.isDebug()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        LoginResponse loginResponse = iamService.login(LoginRequest.builder()
                        .mobileNo(mobileNo)
                        .channel(channel)
                        .code(code)
                        .build()
        );
        log.debug("login user token:{},  code:{}({})", loginResponse.getToken(), loginResponse.getRspCode(), loginResponse.getRspDesc());
        return loginResponse;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(String token,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        if (!iamConfig.isDebug()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        LogoutResponse logoutResponse = iamService.logout(LogoutRequest.builder()
                        .token(token)
                        .build()
        );
        log.debug("logout user,  code:{}({})", logoutResponse.getRspCode(), logoutResponse.getRspDesc());
        return logoutResponse;
    }

}

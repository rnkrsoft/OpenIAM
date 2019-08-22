package com.rnkrsoft.opensource.iam.web.controller;

import com.rnkrsoft.opensource.iam.internal.config.IAMConfig;
import com.rnkrsoft.opensource.iam.domains.LoginRequest;
import com.rnkrsoft.opensource.iam.domains.LoginResponse;
import com.rnkrsoft.opensource.iam.domains.ValidateLoginRequest;
import com.rnkrsoft.opensource.iam.domains.ValidateLoginResponse;
import com.rnkrsoft.opensource.iam.domains.SendSmsRequest;
import com.rnkrsoft.opensource.iam.domains.SendSmsResponse;
import com.rnkrsoft.opensource.iam.enums.ChannelType;
import com.rnkrsoft.opensource.iam.services.IAMService;
import com.rnkrsoft.opensource.iam.cookie.Cookies;
import com.rnkrsoft.opensource.iam.cookie.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 * 短信登录控制器
 */
@Controller
@RequestMapping("/sms")
public class SmsLoginController {
    @Autowired
    IAMConfig iamConfig;
    @Autowired
    IAMService iamService;

    /**
     * 发送短信验证码
     * @param mobileNo 手机号
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public Map sendSms(String mobileNo) {
        Map mode = new HashMap();
        SendSmsResponse response = iamService.send(SendSmsRequest.builder().mobileNo(mobileNo).build());
        mode.put("smsCode", response.getSmsCode());
        mode.put("code", response.getRspCode());
        mode.put("desc", response.getRspDesc());
        return mode;
    }

    /**
     * 进行短信验证码登录
     * @param request HTTP请求
     * @param response HTTP应答
     * @param mobileNo 手机号
     * @param code 短信验证码
     * @param referrer 跳转地址
     * @param attr
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          String mobileNo,
                          String code,
                          String referrer,
                          RedirectAttributes attr) throws UnsupportedEncodingException {
        //调用服务进行短信验证码验证，验证通过，则返回用户令牌
        LoginResponse loginResponse = iamService.login(LoginRequest.builder()
                        .mobileNo(mobileNo)
                        .code(code)
                        .channel(ChannelType.WEB.getCode())
                        .build()
        );
        if (referrer == null || referrer.isEmpty()) {
            referrer = iamConfig.getIamBaseUrl() + "/dashboard";
        }
        if (loginResponse.success()) {//用户通过登录验证
            LoginToken loginToken = LoginToken.builder()
                    .userId(loginResponse.getUserId())
                    .userName(loginResponse.getUserName())
                    .nickName(loginResponse.getNickName())
                    .userAvatar(loginResponse.getUserAvatar())
                    .mobileNo(loginResponse.getMobileNo())
                    .token(loginResponse.getToken())
                    .activeTime(System.currentTimeMillis())
                    .latestLoginTime(System.currentTimeMillis())
                    .currentDepartment(loginResponse.getCurrentDepartment())
                    .build();
            loginToken.getDepartments().addAll(loginResponse.getDepartments());
            Cookies.store(request, response, iamConfig.getCookiePassword(), iamConfig.getDomain(), iamConfig.getCookieName(), loginToken);
            return "redirect:" + referrer;
        } else {//用户验证失败
            referrer = URLEncoder.encode(referrer, "utf8");
            attr.addFlashAttribute("error", loginResponse.getRspDesc());
            return "redirect:" + iamConfig.getIamBaseUrl() + "/iam?referrer=" + referrer;
        }
    }


}

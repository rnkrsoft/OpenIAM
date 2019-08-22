package com.rnkrsoft.opensource.iam.web.controller;

import com.rnkrsoft.message.MessageFormatter;
import com.rnkrsoft.opensource.iam.internal.config.IAMConfig;
import com.rnkrsoft.opensource.iam.security.Base64;
import com.rnkrsoft.opensource.iam.cookie.Cookies;
import com.rnkrsoft.opensource.iam.cookie.LoginToken;
import com.rnkrsoft.opensource.iam.domains.ValidateLoginRequest;
import com.rnkrsoft.opensource.iam.domains.ValidateLoginResponse;
import com.rnkrsoft.opensource.iam.enums.ChannelType;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.CheckQrCodeRequest;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.CheckQrCodeResponse;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.GenerateQrCodeRequest;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.GenerateQrCodeResponse;
import com.rnkrsoft.opensource.iam.internal.qrcode.services.QrCodeLoginService;
import com.rnkrsoft.opensource.iam.services.IAMService;
import com.rnkrsoft.opensource.iam.utils.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Controller
@RequestMapping("/qr")
public class QrCodeLoginController {
    @Autowired
    IAMConfig iamConfig;
    @Autowired
    IAMService iamService;
    @Autowired
    QrCodeLoginService qrCodeLoginService;

    /**
     * 生成二维码
     * @param request HTTP请求
     * @param response HTTP应答
     * @return
     * @throws Exception
     */
    @RequestMapping("/generate")
    @ResponseBody
    public Map generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map mode = new HashMap();
        GenerateQrCodeResponse generateQrCodeResponse = qrCodeLoginService.generate(GenerateQrCodeRequest.builder().build());
        String uuid = generateQrCodeResponse.getUuid();
        String channel = ChannelType.WEB.getCode();
        //生成一个移动端地址，且用参数生成二维码
        String url = MessageFormatter.format("{}?c={}@{}", iamConfig.getMobileUrl(), channel, uuid);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        QRCodeUtil.encode(url, os);
        String qrCode = Base64.encodeToString(os.toByteArray(), Base64.NO_WRAP);
        mode.put("qrCode", qrCode);
        mode.put("uuid", uuid);
        return mode;
    }

    /**
     * 检查随机用户识别码是否通过授权
     * 1.如果超时不存在，则返回二维码已失效；
     * 2.如果存在但未绑定TOKEN，则返回等待授权；
     * 3.如果已绑定用户则将用户信息存放到TOKEN中
     *
     * @param request HTTP请求
     * @param response HTTP应答
     * @return
     * @throws Exception
     */
    @RequestMapping("/check")
    @ResponseBody
    public Map check(String uuid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CheckQrCodeResponse checkQrCodeResponse = qrCodeLoginService.check(CheckQrCodeRequest.builder().uuid(uuid).build());
        if (checkQrCodeResponse.success()){
            LoginToken loginToken = LoginToken.builder()
                    .userId(checkQrCodeResponse.getUserId())
                    .userName(checkQrCodeResponse.getUserName())
                    .nickName(checkQrCodeResponse.getNickName())
                    .userAvatar(checkQrCodeResponse.getUserAvatar())
                    .mobileNo(checkQrCodeResponse.getMobileNo())
                    .token(checkQrCodeResponse.getToken())
                    .activeTime(System.currentTimeMillis())
                    .latestLoginTime(System.currentTimeMillis())
                    .currentDepartment(checkQrCodeResponse.getCurrentDepartment())
                    .build();
            loginToken.getDepartments().addAll(checkQrCodeResponse.getDepartments());
            Cookies.store(request, response, iamConfig.getCookiePassword(),iamConfig.getDomain(), iamConfig.getCookieName(), loginToken);
        }
        Map mode = new HashMap();
        mode.put("code", checkQrCodeResponse.getRspCode());
        mode.put("desc", checkQrCodeResponse.getRspDesc());
        return mode;
    }
}

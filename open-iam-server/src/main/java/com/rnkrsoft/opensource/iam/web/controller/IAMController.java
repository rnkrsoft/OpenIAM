package com.rnkrsoft.opensource.iam.web.controller;

import com.rnkrsoft.opensource.iam.internal.config.IAMConfig;
import com.rnkrsoft.opensource.iam.domains.*;
import com.rnkrsoft.opensource.iam.enums.ChannelType;
import com.rnkrsoft.opensource.iam.services.IAMService;
import com.rnkrsoft.opensource.iam.cookie.Cookies;
import com.rnkrsoft.opensource.iam.cookie.LoginToken;
import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Controller
public class IAMController {
    @Autowired
    IAMConfig iamConfig;
    @Autowired
    IAMService iamService;

    /**
     * 访问IAM首页
     * @param referrer 跳转地址
     * @param request HTTP请求
     * @param response HTTP应答
     * @return 视图对象
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = {"/iam", "/"})
    public ModelAndView iam(String referrer,
                            HttpServletRequest request,
                            HttpServletResponse response){
        LoginToken loginToken = Cookies.load(request, iamConfig.getCookiePassword(), iamConfig.getCookieName());
        if (referrer == null || referrer.isEmpty()){
            referrer = "";
        }
        if (loginToken == null) {
            ModelMap model = new ModelMap();
            model.addAttribute("title", iamConfig.getTitle());
            model.addAttribute("logo", iamConfig.getLogo());
            model.addAttribute("copyright", iamConfig.getCopyright());
            model.addAttribute("desc", iamConfig.getDesc());
            model.addAttribute("referrer", referrer);
            return new ModelAndView("/login", model);
        }
        ValidateLoginResponse validateLoginResponse = iamService.validateLogin(ValidateLoginRequest.builder()
                        .userId(loginToken.getUserId())
                        .token(loginToken.getToken())
                        .build()
        );
        if (!validateLoginResponse.success()) {
            Cookies.delete(request, iamConfig.getCookieName());
            ModelMap model = new ModelMap();
            model.addAttribute("title", iamConfig.getTitle());
            model.addAttribute("logo", iamConfig.getLogo());
            model.addAttribute("copyright", iamConfig.getCopyright());
            model.addAttribute("desc", iamConfig.getDesc());
            model.addAttribute("referrer", referrer);
            return new ModelAndView("/login", model);
        } else {
            //如果没跳转地址，直接重定向到dashboard
            if (referrer.isEmpty()) {
                return new ModelAndView("redirect:" + iamConfig.getIamBaseUrl() + "/dashboard");
            }else{
                return new ModelAndView("redirect:" + referrer);
            }
        }
    }

    /**
     * 切换部门
     * @param departmentId 部门编号
     * @param referrer 跳转地址
     * @param request HTTP请求
     * @param response HTTP应答
     * @return 视图对象
     */
    @RequestMapping("/changeDepartment")
    public ModelAndView changeDepartment(Integer departmentId,
                                         String referrer,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        LoginToken loginToken = Cookies.load(request, iamConfig.getCookiePassword(), iamConfig.getCookieName());
        if (loginToken == null) {
            return new ModelAndView("redirect:" + iamConfig.getIamBaseUrl() + "/");
        }
        if (StringUtils.isBlank(referrer)) {
            referrer = iamConfig.getIamBaseUrl() + "/";
        }
        ChangeDepartmentResponse changeDepartmentResponse = iamService.changeDepartment(ChangeDepartmentRequest.builder()
                        .departmentId(departmentId)
                        .token(loginToken.getToken())
                        .build()
        );
        if (changeDepartmentResponse.success()) {
            loginToken = LoginToken.builder()
                    .userId(changeDepartmentResponse.getUserId())
                    .userName(changeDepartmentResponse.getUserName())
                    .nickName(changeDepartmentResponse.getNickName())
                    .userAvatar(changeDepartmentResponse.getUserAvatar())
                    .mobileNo(changeDepartmentResponse.getMobileNo())
                    .token(changeDepartmentResponse.getToken())
                    .activeTime(System.currentTimeMillis())
                    .latestLoginTime(System.currentTimeMillis())
                    .currentDepartment(changeDepartmentResponse.getCurrentDepartment())
                    .build();
            loginToken.getDepartments().addAll(changeDepartmentResponse.getDepartments());
            Cookies.store(request, response, iamConfig.getCookiePassword(), iamConfig.getDomain(), iamConfig.getCookieName(), loginToken);
            return new ModelAndView("redirect:" + referrer);
        } else {
            return new ModelAndView("redirect:" + referrer);
        }

    }

    /**
     * 注销登录
     * @param request HTTP请求
     * @param response HTTP应答
     * @return 视图对象
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response) {
        LoginToken loginToken = Cookies.load(request, iamConfig.getCookiePassword(), iamConfig.getCookieName());
        if (loginToken != null) {
            LogoutRequest logoutRequest = LogoutRequest.builder()
                    .userId(loginToken.getUserId())
                    .token(loginToken.getToken())
                    .build();
            logoutRequest.getChannels().add(ChannelType.WEB.getCode());
            LogoutResponse logoutResponse = iamService.logout(logoutRequest);
            if (logoutResponse.success()) {
                Cookies.delete(request, iamConfig.getCookieName());
                return new ModelAndView("redirect:" + iamConfig.getIamBaseUrl() + "/");
            } else {
                Cookies.delete(request, iamConfig.getCookieName());
                return new ModelAndView("redirect:" + iamConfig.getIamBaseUrl() + "/");
            }
        } else {
            Cookies.delete(request, iamConfig.getCookieName());
            return new ModelAndView("redirect:" + iamConfig.getIamBaseUrl() + "/");
        }
    }

    /**
     * IAM仪表盘界面
     * @param request HTTP请求
     * @param response HTTP应答
     * @return 视图对象
     */
    @RequestMapping("/dashboard")
    public ModelAndView dashboard(HttpServletRequest request,
                                  HttpServletResponse response) {
        LoginToken loginToken = Cookies.load(request, iamConfig.getCookiePassword(), iamConfig.getCookieName());
        if (loginToken == null) {
            return new ModelAndView("redirect:" + iamConfig.getIamBaseUrl() + "/");
        }
        ValidateLoginResponse validateLoginResponse = iamService.validateLogin(ValidateLoginRequest.builder()
                        .userId(loginToken.getUserId())
                        .token(loginToken.getToken())
                        .build()
        );
        if (!validateLoginResponse.success()) {
            Cookies.delete(request, iamConfig.getCookieName());
            return new ModelAndView("redirect:" + iamConfig.getIamBaseUrl() + "/");
        }
        //根据用户获取该用户授权的应用系统
        FetchApplicationResponse fetchApplicationResponse = iamService.fetchApplication(FetchApplicationRequest.builder()
                        .userId(loginToken.getUserId())
                        .build()
        );
        ModelMap model = new ModelMap();
        //当前部门
        Department currentDepartment = loginToken.getCurrentDepartment();
        if (currentDepartment != null) {
            model.addAttribute("userDepartmentId", currentDepartment.getDepartmentId());
            model.addAttribute("userDepartmentName", currentDepartment.getDepartmentName());
            if (currentDepartment.getBranch() != null) {
                model.addAttribute("userBranchId", currentDepartment.getBranch().getBranchId());
                model.addAttribute("userBranchName", currentDepartment.getBranch().getBranchName());
            }
            if (currentDepartment.getCity() != null) {
                model.addAttribute("userCityId", currentDepartment.getCity().getCityId());
                model.addAttribute("userCityName", currentDepartment.getCity().getCityName());
            }
        }
        model.addAttribute("changeDepartmentUrl", iamConfig.getIamBaseUrl() + "/changeDepartment");
        model.addAttribute("departments", loginToken.getDepartments());
        model.addAttribute("debug", iamConfig.isDebug());
        model.addAttribute("title", iamConfig.getTitle());
        model.addAttribute("logo", iamConfig.getLogo());
        model.addAttribute("apps", fetchApplicationResponse.getRecords());
        model.addAttribute("user", loginToken);
        return new ModelAndView("/dashboard", model);
    }
}

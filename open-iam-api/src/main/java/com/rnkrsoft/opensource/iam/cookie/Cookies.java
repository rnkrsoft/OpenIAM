package com.rnkrsoft.opensource.iam.cookie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rnkrsoft.opensource.iam.security.AES;
import com.rnkrsoft.opensource.iam.security.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 * Cookie工具类
 */
public class Cookies {
    static Gson GSON = new GsonBuilder().serializeNulls().create();

    /**
     * 加载存储的加密cookie内容
     *
     * @param request    请求对象
     * @param password   加密密码
     * @param cookieName cookie名
     * @return 登录令牌对象
     */
    public static LoginToken load(HttpServletRequest request, String password, String cookieName) {
        String value = "";
        Cookie[] cookies = request.getCookies();
        Cookie loginCookie = null;
        if (cookies == null) {
            return null;
        }
        boolean foundManyToken = false;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (cookieName.equalsIgnoreCase(name)) {
                if (loginCookie == null) {
                    value = cookie.getValue();
                    loginCookie = cookie;
                } else {
                    foundManyToken = true;
                }
            }
        }
        //发现多个Token的情况，说明是测试环境
        if (foundManyToken) {
            delete(request, cookieName);
            return null;
        }
        //如果cookie中无值，清空cookie
        if (value == null || value.isEmpty() || loginCookie == null) {
            delete(request, cookieName);
            return null;
        }

        String result = null;
        try {
            result = AES.decrypt(password, value);
        } catch (RuntimeException e) {
            //解密cookie发生错误，清空cookie
            delete(request, cookieName);
            return null;
        }
        if (result == null) {
            //如果cookie无效，清空cookie
            delete(request, cookieName);
            return null;
        }
        loginCookie.setMaxAge(10 * 60);
        LoginToken token = GSON.fromJson(result, LoginToken.class);
        return token;

    }

    /**
     * 存储登录令牌到cookie中
     *
     * @param request    请求对象
     * @param response   应答对象
     * @param password   加密密码
     * @param domain     域名
     * @param cookieName cookie名
     * @param loginToken 登录令牌对象
     */
    public static void store(HttpServletRequest request, HttpServletResponse response, String password, String domain, String cookieName, LoginToken loginToken) {
        if (loginToken == null || response == null) {
            return;
        }
        String json = AES.encrypt(password, GSON.toJson(loginToken));
        delete(request, cookieName);
        Cookie cookie = new Cookie(cookieName, json);
        cookie.setPath("/");
        cookie.setDomain(domain);
        //10分钟有效
        cookie.setMaxAge(10 * 60);
        response.addCookie(cookie);
    }

    public static void delete(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (cookieName.equalsIgnoreCase(name)) {
                //不记录cookie
                cookie.setMaxAge(0);
                cookie.setValue("");
            }
        }
    }
}
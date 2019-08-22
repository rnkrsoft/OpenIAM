package com.rnkrsoft.opensource.iam.gateway.user.facade;

import com.rnkrsoft.opensource.iam.gateway.user.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@ApidocService(value = "用户服务", channel = "iam")
public interface UserFacade {
    @ApidocInterface(value = "获取我的信息", name = "201", version = "1", usage = "获取当前登录用户的详细信息")
    GetMyInfoResponse getMyInfo(GetMyInfoRequest request);
    @ApidocInterface(value = "获取用户信息", name = "202", version = "1", usage = "用于根据已知用户编号情况下获取用户的详细信息")
    GetUserDetailResponse getUserDetail(GetUserDetailRequest request);
    @ApidocInterface(value = "搜索用户", name = "203", version = "1", usage = "根据搜索关键字搜索用户")
    SearchUserResponse searchUser(SearchUserRequest request);
}

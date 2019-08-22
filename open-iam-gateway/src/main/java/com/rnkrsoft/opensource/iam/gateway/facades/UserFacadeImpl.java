package com.rnkrsoft.opensource.iam.gateway.facades;

import com.rnkrsoft.opensource.iam.domains.GetUserRequest;
import com.rnkrsoft.opensource.iam.domains.GetUserResponse;
import com.rnkrsoft.opensource.iam.gateway.user.domains.*;
import com.rnkrsoft.opensource.iam.gateway.user.facade.UserFacade;
import com.rnkrsoft.opensource.iam.services.IAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Service
public class UserFacadeImpl implements UserFacade {
    @Autowired
    IAMService iamService;

    @Override
    public GetMyInfoResponse getMyInfo(GetMyInfoRequest request) {
        GetMyInfoResponse response = new GetMyInfoResponse();
        GetUserResponse getUserResponse = iamService.getUser(GetUserRequest.builder().userId(request.getUserId()).build());
        if (!getUserResponse.success()){
            response.setRspCode(getUserResponse.getRspCode());
            response.setRspDesc(getUserResponse.getRspDesc());
            return response;
        }
        response = GetMyInfoResponse.builder()
                .mobileNo(getUserResponse.getMobileNo())
                .channel(getUserResponse.getChannel())
                .nickName(getUserResponse.getNickName())
                .userId(getUserResponse.getUserId())
                .userName(getUserResponse.getUserName())
                .nickName(getUserResponse.getNickName())
                .userAvatar(getUserResponse.getUserAvatar())
                .fistLogin(getUserResponse.getFistLogin())
                .currentDepartment(getUserResponse.getCurrentDepartment())
                .build();
        response.getDepartments().addAll(getUserResponse.getDepartments());
        return response;
    }

    @Override
    public GetUserDetailResponse getUserDetail(GetUserDetailRequest request) {
        return null;
    }

    @Override
    public SearchUserResponse searchUser(SearchUserRequest request) {
        return null;
    }
}

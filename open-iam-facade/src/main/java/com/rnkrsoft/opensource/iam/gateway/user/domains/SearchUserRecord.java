package com.rnkrsoft.opensource.iam.gateway.user.domains;

import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
public class SearchUserRecord implements Serializable{
    String userId;
    String userName;
    /**
     * 用户头像
     */
    String userAvatar;
    String mobileNo;
    String nickName;
    String jobId;
    String jobName;
    String branchId;
    String branchName;
}

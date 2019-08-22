package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Data
public class QueryUserBO implements Serializable{
    String userId;
    String userName;
    String nickName;
    String userAvatar;
    String mobileNo;
    Integer userStatus;
    Boolean firstLogin;
    Integer latestDepartmentId;
    String roles;
    String branches;
    String jobs;
    String departments;
    String cities;
    Date createDate;
    Date lastUpdateDate;
}

package com.rnkrsoft.opensource.iam.cookie;

import com.rnkrsoft.opensource.iam.vo.Department;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@Builder
public class LoginToken {
    /**
     * 用户号
     */
    String userId;
    /**
     * 用户名
     */
    String userName;
    /**
     * 昵称
     */
    String nickName;
    /**
     * 用户头像
     */
    String userAvatar;
    /**
     * 手机号码
     */
    String mobileNo;
    /**
     * 令牌
     */
    String token;
    /**
     * 最近登录时间
     */
    Long latestLoginTime;
    /**
     * 活动时间
     */
    Long activeTime;
    /**
     * 当前选中的部门
     */
    Department currentDepartment;
    /**
     * 所有部门列表
     */
    final List<Department> departments = new ArrayList();
}

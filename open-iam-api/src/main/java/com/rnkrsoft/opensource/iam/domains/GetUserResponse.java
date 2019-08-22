package com.rnkrsoft.opensource.iam.domains;

import com.rnkrsoft.opensource.iam.enums.UserStatus;
import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.opensource.iam.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse extends AbstractResponse{
    /**
     * 用户编号
     */
    String userId;
    /**
     * 用户姓名
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
     * 手机号
     */
    String mobileNo;
    /**
     * 令牌
     */
    String token;
    /**
     * 是否首次登录 0否 1是
     */
    Integer fistLogin;
    /**
     * 通道
     */
    String channel;

    @ApidocElement(value = "用户状态", required = true, enumClass = UserStatus.class)
    Integer userStatus;
    /**
     * 当前部门
     */
    Department currentDepartment;
    /**
     * 部门列表
     */
    final List<Department> departments = new ArrayList();
}

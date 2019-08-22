package com.rnkrsoft.opensource.iam.gateway.user.domains;

import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.opensource.iam.vo.Role;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woate on 2019/7/14.
 */
public class GetUserDetailResponse extends AbstractResponse{
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
     * 通道
     */
    String channel;
    /**
     * 当前部门
     */
    Department currentDepartment;
    /**
     * 部门列表
     */
    final List<Department> departments = new ArrayList();
    /**
     * 角色列表
     */
    final List<Role> roles = new ArrayList();
}

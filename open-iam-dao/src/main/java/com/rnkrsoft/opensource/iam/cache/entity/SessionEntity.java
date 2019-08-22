package com.rnkrsoft.opensource.iam.cache.entity;

import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.opensource.iam.vo.Role;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SessionEntity implements Serializable{
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
     * 通道
     */
    String channel;
    /**
     * 当前部门
     */
    Department currentDepartment;
    /**
     * 部门信息列表，一个人可能位于多个部门
     */
    final List<Department> departments = new ArrayList();
    /**
     * 角色列表
     */
    final List<Role> roles = new ArrayList();

}
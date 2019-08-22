package com.rnkrsoft.opensource.iam.internal.qrcode.domains;

import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.opensource.iam.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.AbstractResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckQrCodeResponse extends AbstractResponse{
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
    /**
     * 最新的部门列表
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

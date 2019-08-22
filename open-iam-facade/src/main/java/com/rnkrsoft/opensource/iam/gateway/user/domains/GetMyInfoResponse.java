package com.rnkrsoft.opensource.iam.gateway.user.domains;

import com.rnkrsoft.opensource.iam.enums.SexType;
import com.rnkrsoft.opensource.iam.enums.YesOrNo;
import com.rnkrsoft.opensource.iam.vo.Department;
import com.rnkrsoft.opensource.iam.vo.Role;
import lombok.*;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class GetMyInfoResponse extends AbstractResponse {
    @ApidocElement("用户编号")
    String userId;

    @ApidocElement("用户姓名")
    String userName;

    @ApidocElement("昵称")
    String nickName;

    @ApidocElement("用户头像")
    String userAvatar;

    @ApidocElement("手机号")
    String mobileNo;

    @ApidocElement(value = "性别", enumClass = SexType.class)
    Integer sex;

    @ApidocElement(value = "是否首次登录 0否 1是", enumClass = YesOrNo.class)
    Integer fistLogin;

    @ApidocElement("通道")
    String channel;

    @ApidocElement("当前部门")
    Department currentDepartment;

    @ApidocElement("部门列表")
    final List<Department> departments = new ArrayList();
}

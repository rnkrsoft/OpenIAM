package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import com.rnkrsoft.opensource.iam.enums.UserStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeDisplayRule;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryUserRecord implements Serializable {
    @ApidocElement(value = "用户号", maxLen = 10, unique = true, interfaces = {
            @WebCascadeInterface(displayName = "删除",
                    serviceClass = UserService.class,
                    value = "logicDelete", confirm = true,
                    confirmMessage = "确认是否删除'${userName}'用户?"
            ),
            @WebCascadeInterface(displayName = "修改",
                    serviceClass = UserService.class,
                    value = "toUpdate"
            ),
            @WebCascadeInterface(displayName = "启用",
                    serviceClass = UserService.class,
                    value = "enable", confirm = true,
                    confirmMessage = "确认是否启用'${userName}'用户?",
                    rules = @WebCascadeDisplayRule("${status} == 2")
            ),
            @WebCascadeInterface(displayName = "禁用",
                    serviceClass = UserService.class,
                    value = "disable", confirm = true,
                    confirmMessage = "确认是否禁用'${userName}'用户?",
                    rules = @WebCascadeDisplayRule("${status} == 1")
            ),
            @WebCascadeInterface(displayName = "查看",
                    serviceClass = UserService.class,
                    value = "view"
            )
    })
    String userId;
    @ApidocElement(value = "用户名", maxLen = 20)
    String userName;
    @ApidocElement(value = "昵称", maxLen = 20)
    String nickName;
    @ApidocElement(value = "用户头像", maxLen = 20)
    String userAvatar;
    @ApidocElement(value = "手机号", maxLen = 20)
    String mobileNo;
    @ApidocElement(value = "拥有角色", maxLen = 20)
    String roleName;
    @ApidocElement(value = "分支机构", maxLen = 20)
    String branchName;
    @ApidocElement(value = "部门名称", maxLen = 20)
    String departmentName;
    @ApidocElement(value = "职位名称", maxLen = 20)
    String jobName;
    @ApidocElement(value = "状态", maxLen = 20, enumClass = UserStatus.class)
    Integer status;
}

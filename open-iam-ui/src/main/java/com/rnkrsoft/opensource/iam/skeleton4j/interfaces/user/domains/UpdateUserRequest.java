package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class UpdateUserRequest implements Serializable {
    @ApidocElement(value = "用户号", unique = true, hidden = true)
    String userId;
    @ApidocElement(value = "用户名", required = true)
    String userName;
    @ApidocElement(value = "昵称", required = true)
    String nickName;
    @ApidocElement(value = "用户头像", required = false, valueDisplayType = ValueDisplayType.IMAGE)
    String userAvatar;
    @ApidocElement(value = "手机号", required = true, maxLen = 11, minLen = 11)
    String mobileNo;
    @ApidocElement(value = "岗位", required = false)
    final Set<Integer> jobs = new HashSet();
    @ApidocElement(value = "角色", required = false)
    final Set<Integer> roles = new HashSet();
}

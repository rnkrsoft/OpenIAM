package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import com.rnkrsoft.opensource.iam.enums.UserStatus;
import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class ViewUserResponse extends AbstractResponse{
    @ApidocElement(value = "用户号", unique = true, hidden = false)
    String userId;
    @ApidocElement(value = "用户名", required = true)
    String userName;
    @ApidocElement(value = "昵称", required = true)
    String nickName;
    @ApidocElement(value = "用户头像", required = false, valueDisplayType = ValueDisplayType.IMAGE)
    String userAvatar;
    @ApidocElement(value = "手机号", required = true, maxLen = 11, minLen = 11)
    String mobileNo;
    @ApidocElement(value = "用户状态", required = true, enumClass = UserStatus.class)
    Integer userStatus;
    @ApidocElement(value = "职位", valueDisplayType = ValueDisplayType.TABLE)
    final List<ViewUserJobRecord> jobs = new ArrayList();
}

package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import com.rnkrsoft.opensource.iam.enums.UserStatus;
import lombok.*;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;


/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
public class QueryUserRequest extends AbstractRequestPage {
    @ApidocElement(value = "用户名",required = false)
    String userName;
    @ApidocElement(value = "昵称",required = false)
    String nickName;
    @ApidocElement(value = "手机号",required = false)
    String mobileNo;
    
    @ApidocElement(value = "状态", enumClass = UserStatus.class, required = false)
    Integer userStatus;
}

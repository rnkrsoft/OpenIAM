package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import com.rnkrsoft.opensource.iam.enums.UserStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services.CityService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.services.JobService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services.RoleService;
import lombok.*;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;
import javax.web.doc.enums.ValueDisplayType;
import javax.web.doc.enums.WebDisplayType;
import javax.web.doc.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;
import java.util.HashSet;
import java.util.Set;

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

package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.services.DepartmentService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.services.JobService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services.RoleService;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.enums.ValueDisplayType;
import javax.web.skeleton4j.enums.WebDisplayType;
import javax.web.skeleton4j.enums.WebLayout;
import javax.web.skeleton4j.enums.WebTriggerEvent;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import javax.web.skeleton4j.annotation.WebResultDisplay;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class CreateUserRequest implements Serializable{
    @ApidocElement(value = "用户号", required = false, placeholder = "可以手工输入，如果不输入则生成 uuid")
    String userId;
    @ApidocElement(value = "用户名", required = true)
    String userName;
    @ApidocElement(value = "昵称", required = true)
    String nickName;
    @ApidocElement(value = "用户头像", required = false, valueDisplayType = ValueDisplayType.IMAGE)
    String userAvatar;
    @ApidocElement(value = "手机号", required = true, maxLen = 11, minLen = 11)
    String mobileNo;
    @ApidocElement(value = "岗位", required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = JobService.class,
                    value = "fetchJob=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    final Set<Integer> jobs = new HashSet();
    @ApidocElement(value = "角色", required = false, valueDisplayType = ValueDisplayType.SELECTION, interfaces = {
            @WebCascadeInterface(serviceClass = RoleService.class,
                    value = "fetchRole=fetch",
                    cascadeEvent = WebTriggerEvent.INIT,
                    resultDisplay = @WebResultDisplay(displayType = WebDisplayType.DATA_SOURCE)
            )
    })
    final Set<Integer> roles = new HashSet();
}

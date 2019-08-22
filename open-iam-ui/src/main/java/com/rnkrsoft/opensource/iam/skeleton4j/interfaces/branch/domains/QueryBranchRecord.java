package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains;

import com.rnkrsoft.opensource.iam.enums.BranchStatus;
import com.rnkrsoft.opensource.iam.enums.DepartmentStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryBranchRecord implements Serializable{
    @ApidocElement(value = "机构编号", unique = true, hidden = true, maxLen = 10, interfaces = {
            @WebCascadeInterface(displayName = "编辑", serviceClass = BranchService.class, value = "toUpdate"),
            @WebCascadeInterface(displayName = "删除", serviceClass = BranchService.class, value = "delete", confirm = true)
    })
    Integer branchId;
    @ApidocElement(value = "机构名称", maxLen = 36)
    String branchName;
    @ApidocElement(value = "所属城市", maxLen = 15)
    String cityName;
    @ApidocElement(value = "状态", maxLen = 10, enumClass = BranchStatus.class)
    Integer branchStatus;
}

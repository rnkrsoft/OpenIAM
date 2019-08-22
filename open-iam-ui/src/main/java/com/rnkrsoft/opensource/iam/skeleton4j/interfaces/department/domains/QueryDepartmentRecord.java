package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.domains;

import com.rnkrsoft.opensource.iam.enums.DepartmentStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.department.services.DepartmentService;
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
public class QueryDepartmentRecord implements Serializable{
    @ApidocElement(value = "部门编号", maxLen = 10, unique = true, hidden = true, interfaces = {
            @WebCascadeInterface(displayName = "删除", serviceClass = DepartmentService.class, value = "delete", confirm = true),
            @WebCascadeInterface(displayName = "编辑", serviceClass = DepartmentService.class, value = "toUpdate")
    })
    Integer departmentId;
    @ApidocElement(value = "部门名称", maxLen = 36)
    String departmentName;
    @ApidocElement(value = "分支机构", maxLen = 36)
    String branchName;
    @ApidocElement(value = "城市", maxLen = 10)
    String cityName;
    @ApidocElement(value = "状态", maxLen = 10, enumClass = DepartmentStatus.class)
    Integer departmentStatus;
}

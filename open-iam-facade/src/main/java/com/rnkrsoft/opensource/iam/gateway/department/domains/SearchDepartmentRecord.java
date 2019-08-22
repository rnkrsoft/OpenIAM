package com.rnkrsoft.opensource.iam.gateway.department.domains;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
public class SearchDepartmentRecord implements Serializable{
    @ApidocElement("部门编号")
    String departmentId;
    @ApidocElement("部门名称")
    String departmentName;
    @ApidocElement("分支机构编号")
    String branchId;
    @ApidocElement("分支机构名称")
    String branchName;
    @ApidocElement("城市编号")
    String cityId;
    @ApidocElement("城市名称")
    String cityName;
    @ApidocElement("部门图标")
    String departmentIcon;
    @ApidocElement("用户总数")
    Integer userTotal;
}

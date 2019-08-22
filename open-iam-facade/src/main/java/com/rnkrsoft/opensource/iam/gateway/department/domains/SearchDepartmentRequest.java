package com.rnkrsoft.opensource.iam.gateway.department.domains;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
public class SearchDepartmentRequest implements Serializable{
    @ApidocElement("关键字，支持部门名称，分支机构名称,允许为空")
    String keyword;
}

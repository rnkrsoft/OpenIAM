package com.rnkrsoft.opensource.iam.vo;

import lombok.Builder;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
@Data
@Builder
public class Department implements Serializable{
    @ApidocElement("部门编号")
    Integer departmentId;
    @ApidocElement("部门名称")
    String departmentName;
    @ApidocElement("分支结构")
    Branch branch;
    @ApidocElement("城市")
    City city;
    @ApidocElement("岗位")
    Job Job;
}

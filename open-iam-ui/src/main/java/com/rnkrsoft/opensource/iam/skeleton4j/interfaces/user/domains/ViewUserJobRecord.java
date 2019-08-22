package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewUserJobRecord implements Serializable{
    @ApidocElement(value = "岗位映射编号", hidden = true)
    Integer jobMappingId;
    @ApidocElement(value = "部门")
    String departmentName;
    @ApidocElement(value = "岗位")
    String jobName;
}

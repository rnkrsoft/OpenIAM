package com.rnkrsoft.opensource.iam.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/11.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeDepartmentRequest implements Serializable{
    @ApidocElement("部门编号")
    Integer departmentId;
    @ApidocElement("用户令牌")
    String token;
}

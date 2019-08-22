package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class UpdateBranchRequest implements Serializable{
    @ApidocElement(value = "分支机构编号", maxLen = 10, unique = true)
    Integer branchId;
    @ApidocElement(value = "分支机构名称", maxLen = 10)
    String branchName;
    @ApidocElement(value = "城市", maxLen = 10)
    Integer cityId;
}

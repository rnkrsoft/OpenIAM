package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class DeleteBranchRequest implements Serializable{
    @ApidocElement(value = "机构编号", maxLen = 10, unique = true)
    Integer branchId;
}

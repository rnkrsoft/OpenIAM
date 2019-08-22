package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.domains;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.branch.services.BranchService;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class ToUpdateBranchRequest implements Serializable{
    @ApidocElement(value = "机构编号", unique = true, maxLen = 10, interfaces = {
            @WebCascadeInterface(serviceClass = BranchService.class, value = "update", confirm = true)
    })
    Integer branchId;
}

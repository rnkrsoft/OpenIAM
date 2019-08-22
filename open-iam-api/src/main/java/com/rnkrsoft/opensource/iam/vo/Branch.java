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
public class Branch implements Serializable{
    @ApidocElement("分支机构编号")
    Integer branchId;

    @ApidocElement("分支机构名称")
    String branchName;
}

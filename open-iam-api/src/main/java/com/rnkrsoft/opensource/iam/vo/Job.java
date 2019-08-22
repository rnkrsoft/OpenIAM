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
public class Job implements Serializable{
    @ApidocElement("岗位编号")
    Integer jobId;
    @ApidocElement("岗位名称")
    String jobName;
}

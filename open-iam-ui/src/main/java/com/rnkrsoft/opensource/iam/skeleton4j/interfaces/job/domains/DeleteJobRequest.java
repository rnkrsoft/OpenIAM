package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.job.domains;


import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class DeleteJobRequest  implements Serializable {
    @ApidocElement(value = "岗位编号", maxLen = 36, unique = true)
    Integer jobId;
}

package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class DeleteApplicationRequest implements Serializable{
    @ApidocElement(value = "应用编号", unique = true, maxLen = 10)
    Integer appId;
}

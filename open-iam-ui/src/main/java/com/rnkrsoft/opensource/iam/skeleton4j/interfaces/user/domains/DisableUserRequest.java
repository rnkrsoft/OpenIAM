package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.user.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class DisableUserRequest implements Serializable{
    @ApidocElement(value = "用户号", unique = true, hidden = true)
    String userId;
}

package com.rnkrsoft.opensource.iam.gateway.user.domains;

import com.rnkrsoft.platform.protocol.TokenAble;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class GetUserDetailRequest implements Serializable, TokenAble{
    @ApidocElement("用户编号")
    String userId;
    String token;
}

package com.rnkrsoft.opensource.iam.gateway.user.domains;

import com.rnkrsoft.platform.protocol.TokenAble;
import com.rnkrsoft.platform.protocol.UserInfoAble;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
public class GetMyInfoRequest implements Serializable, TokenAble, UserInfoAble{
    String token;
    String userId;
    String userName;
}

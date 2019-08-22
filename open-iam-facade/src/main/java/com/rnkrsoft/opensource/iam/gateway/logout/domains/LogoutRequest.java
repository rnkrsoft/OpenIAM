package com.rnkrsoft.opensource.iam.gateway.logout.domains;

import com.rnkrsoft.platform.protocol.TokenAble;
import com.rnkrsoft.platform.protocol.UserInfoAble;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class LogoutRequest implements Serializable, TokenAble, UserInfoAble{
    String token;
    String userId;
    String userName;
}

package com.rnkrsoft.opensource.iam.gateway.login.domains;

import com.rnkrsoft.opensource.iam.enums.ChannelType;
import com.rnkrsoft.platform.protocol.TokenAble;
import com.rnkrsoft.platform.protocol.UserInfoAble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckUuidRequest implements Serializable, TokenAble, UserInfoAble {
    @ApidocElement(value = "随机用户识别码", maxLen = 36)
    String uuid;
    @ApidocElement(value = "渠道", maxLen = 36, enumClass = ChannelType.class, defaults = "WEB")
    String channel;
    String token;
    String userId;
    String userName;
}

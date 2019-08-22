package com.rnkrsoft.opensource.iam.domains;

import com.rnkrsoft.opensource.iam.enums.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeUserRequest implements Serializable{
    @ApidocElement("用户标识,通过其他渠道获取的")
    String uuid;
    @ApidocElement(value = "渠道，uid对应的渠道", enumClass = ChannelType.class)
    String channel;
    @ApidocElement("已登录用户令牌")
    String token;
    @ApidocElement("已登录用户号")
    String userId;
    @ApidocElement("已登录用户名")
    String userName;
}

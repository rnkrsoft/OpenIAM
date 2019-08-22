package com.rnkrsoft.opensource.iam.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest implements Serializable{
    @ApidocElement("用户号")
    String userId;
    @ApidocElement("登出渠道")
    final List<String> channels = new ArrayList();
    @ApidocElement("用户令牌")
    String token;
}

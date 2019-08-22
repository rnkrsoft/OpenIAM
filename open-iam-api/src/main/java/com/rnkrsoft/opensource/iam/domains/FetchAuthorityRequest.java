package com.rnkrsoft.opensource.iam.domains;

import lombok.*;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FetchAuthorityRequest implements Serializable {
    @ApidocElement(value = "会话ID", maxLen = 36)
    String sessionId;

    @ApidocElement("应用代码")
    String appCode;

    @ApidocElement("用户编号")
    String userId;

    @ApidocElement("用户令牌")
    String token;
}
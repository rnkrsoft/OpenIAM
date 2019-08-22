package com.rnkrsoft.opensource.iam.domains;

import lombok.*;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ValidateLoginRequest implements Serializable{
    @ApidocElement("用户编号")
    String userId;
    @ApidocElement("令牌")
    String token;
}
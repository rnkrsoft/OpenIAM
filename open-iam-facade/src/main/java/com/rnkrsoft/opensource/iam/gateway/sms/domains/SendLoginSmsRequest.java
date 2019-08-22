package com.rnkrsoft.opensource.iam.gateway.sms.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendLoginSmsRequest implements Serializable {
    @ApidocElement(value = "手机号码", minLen = 11, maxLen = 11)
    String mobileNo;
}

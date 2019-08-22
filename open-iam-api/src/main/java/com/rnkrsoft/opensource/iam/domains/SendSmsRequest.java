package com.rnkrsoft.opensource.iam.domains;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@Builder
public class SendSmsRequest implements Serializable{
    String mobileNo;
}

package com.rnkrsoft.opensource.iam.gateway.notice.domains;

import lombok.Data;

import javax.web.skeleton4j.session.LoginUserId;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/17.
 */
@Data
public class GetNoticeRequest implements Serializable, LoginUserId{
    String loginUserId;
}
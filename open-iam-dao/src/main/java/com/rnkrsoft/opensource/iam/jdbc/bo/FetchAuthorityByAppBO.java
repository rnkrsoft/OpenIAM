package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@Data
public class FetchAuthorityByAppBO implements Serializable{
    Integer appId;
    String appCode;
    String appTitle;
    Integer productId;
    String productCode;
    String productTitle;
    Integer actionId;
    String actionCode;
    String actionTitle;
    Integer subActionId;
    String subActionCode;
    String subActionTitle;
}

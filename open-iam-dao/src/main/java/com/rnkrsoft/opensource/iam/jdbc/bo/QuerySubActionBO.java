package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@Data
public class QuerySubActionBO implements Serializable{
    Integer subActionId;
    String subActionCode;
    String subActionTitle;
    String actionTitle;
    String productTitle;
    String appTitle;
    Integer subActionStatus;
    Date createDate;
    Date lastUpdateDate;
}

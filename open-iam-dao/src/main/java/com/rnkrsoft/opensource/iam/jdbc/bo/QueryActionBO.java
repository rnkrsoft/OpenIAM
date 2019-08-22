package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryActionBO implements Serializable{
    Integer actionId;
    String actionCode;
    String applicationTitle;
    String productTitle;
    String actionTitle;
    Integer actionStatus;
    Date createDate;
    Date lastUpdateDate;
}

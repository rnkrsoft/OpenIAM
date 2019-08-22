package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@Data
public class QueryProductBO implements Serializable{
    Integer productId;
    String productCode;
    String productTitle;
    String appTitle;
    Integer productStatus;
    Date createDate;
    Date lastUpdateDate;
}

package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/11.
 */
@Data
public class QueryAuthorityBO implements Serializable{
    Integer authorityId;
    Integer resourceType;
    Integer resourceId;
    String resourceCode;
    String resourceName;
}

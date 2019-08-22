package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Data
public class QueryDepartmentBO implements Serializable{
    Integer departmentId;
    String departmentName;
    String branchName;
    String cityName;
    Integer departmentStatus;
}

package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by woate on 2019/7/17.
 */
@Data
public class QueryJobBO implements Serializable{
    Integer jobId;
    String jobName;
    Integer jobStatus;
    Integer departmentId;
    String departmentName;
    Integer branchId;
    String branchName;
    Integer cityId;
    String cityName;

}

package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
public class SelectJobBO implements Serializable{
    Integer mappingId;
    Integer jobId;
    String jobName;
    Integer departmentId;
    String departmentName;
    Integer branchId;
    String branchName;
    Integer cityId;
    String cityName;
}

package com.rnkrsoft.opensource.iam.jdbc.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/11.
 */
@Data
public class GetUserInfoBO implements Serializable{
    Integer departmentId;
    String departmentName;
    Integer branchId;
    String branchName;
    Integer cityId;
    String cityName;
    Integer jobId;
    String jobName;
}

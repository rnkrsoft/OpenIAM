package com.rnkrsoft.opensource.iam.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
@Data
@Builder
public class Role implements Serializable{
    Integer roleId;
    String roleName;
}

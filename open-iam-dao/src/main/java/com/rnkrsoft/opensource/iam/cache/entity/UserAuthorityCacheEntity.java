package com.rnkrsoft.opensource.iam.cache.entity;

import com.rnkrsoft.opensource.iam.jdbc.bo.FetchAuthorityByAppAndUserBO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
@ToString
public class UserAuthorityCacheEntity implements Serializable{
    final List<FetchAuthorityByAppAndUserBO> records = new ArrayList();
}

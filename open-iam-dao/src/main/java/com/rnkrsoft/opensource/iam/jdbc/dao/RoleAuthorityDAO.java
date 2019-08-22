package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.entity.RoleAuthorityEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * Created by rnkrsoft.com on 2018/10/24.
 */
public interface RoleAuthorityDAO extends JdbcMapper<RoleAuthorityEntity, String> {
    void insertAuthorities(@Param("roleId")Integer roleId, @Param("subActions")Set<Integer> subActions);
}

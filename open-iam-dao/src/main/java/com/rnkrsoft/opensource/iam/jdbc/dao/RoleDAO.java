package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rnkrsoft.com on 2018/10/24.
 */
public interface RoleDAO extends JdbcMapper<RoleEntity, Integer> {
    /**
     * 根据用户编号查询角色列表
     * @param userId
     * @return
     */
    List<RoleEntity> selectByUserId(@Param("userId")String userId);
}

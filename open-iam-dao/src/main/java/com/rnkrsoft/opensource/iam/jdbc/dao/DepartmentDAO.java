package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.bo.GetUserInfoBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryDepartmentBO;
import com.rnkrsoft.opensource.iam.jdbc.entity.DepartmentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * rnkrsoft.com 框架自动生成!
 */
public interface DepartmentDAO extends JdbcMapper<DepartmentEntity, Integer> {
    Pagination<QueryDepartmentBO> queryDepartment(Pagination<QueryDepartmentBO> pagination);
    /**
     * 根据用户号，查询所处部门
     * @param userId
     * @return
     */
    List<DepartmentEntity> selectByUserId(@Param("userId")String userId);
}

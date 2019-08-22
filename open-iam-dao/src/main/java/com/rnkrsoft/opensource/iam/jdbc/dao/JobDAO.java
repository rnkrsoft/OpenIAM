package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryJobBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.SelectJobBO;
import com.rnkrsoft.opensource.iam.jdbc.entity.JobEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * rnkrsoft.com 框架自动生成!
 */
public interface JobDAO extends JdbcMapper<JobEntity, Integer> {
    /**
     * 根据用户编号获取岗位列表
     * @param userId
     * @return
     */
    List<JobEntity> selectByUserId(@Param("userId")String userId);

    List<SelectJobBO> selectJobByUserId(@Param("userId")String userId);

    Pagination<QueryJobBO> queryJob(Pagination<QueryJobBO> pagination);
}

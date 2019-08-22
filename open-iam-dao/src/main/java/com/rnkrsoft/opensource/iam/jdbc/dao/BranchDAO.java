package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryBranchBO;
import com.rnkrsoft.opensource.iam.jdbc.entity.BranchEntity;

/**
 * Created by rnkrsoft.com on 2018/10/24.
 */
public interface BranchDAO extends JdbcMapper<BranchEntity, Integer> {
    Pagination<QueryBranchBO> queryBranch(Pagination<QueryBranchBO> pagination);
}

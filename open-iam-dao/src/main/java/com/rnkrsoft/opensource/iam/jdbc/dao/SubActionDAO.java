package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.bo.QuerySubActionBO;
import com.rnkrsoft.opensource.iam.jdbc.entity.SubActionEntity;

/**
 * Created by rnkrsoft.com on 2018/10/23.
 */
public interface SubActionDAO extends JdbcMapper<SubActionEntity, String> {
    Pagination<QuerySubActionBO> querySubAction(Pagination<QuerySubActionBO> pagination);
}

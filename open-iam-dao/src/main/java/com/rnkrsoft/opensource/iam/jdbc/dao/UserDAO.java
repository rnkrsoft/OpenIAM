package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryUserBO;
import com.rnkrsoft.opensource.iam.jdbc.entity.UserEntity;

/**
 * Created by rnkrsoft.com on 2018/10/24.
 */
public interface UserDAO extends JdbcMapper<UserEntity, String> {
    Pagination<QueryUserBO> queryUser(Pagination<QueryUserBO> pagination);
}

package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryProductBO;
import com.rnkrsoft.opensource.iam.jdbc.entity.ProductEntity;

/**
 * Created by rnkrsoft.com on 2018/10/23.
 */
public interface ProductDAO extends JdbcMapper<ProductEntity, String> {
    Pagination<QueryProductBO> queryProduct(Pagination<QueryProductBO> pagination);
}

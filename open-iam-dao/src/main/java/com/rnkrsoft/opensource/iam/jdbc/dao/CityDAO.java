package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.entity.CityEntity;

/**
 * Created by rnkrsoft.com on 2018/10/24.
 */
public interface CityDAO extends JdbcMapper<CityEntity, Integer> {
    Pagination<CityEntity> queryCity(Pagination<CityEntity> pagination);
}

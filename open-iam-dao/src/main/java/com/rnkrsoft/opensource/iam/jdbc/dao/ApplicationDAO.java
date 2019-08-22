package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.entity.ApplicationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rnkrsoft.com on 2018/10/23.
 */
public interface ApplicationDAO extends JdbcMapper<ApplicationEntity, Integer> {

}

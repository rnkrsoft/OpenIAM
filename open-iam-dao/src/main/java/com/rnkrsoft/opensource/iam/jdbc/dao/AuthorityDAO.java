package com.rnkrsoft.opensource.iam.jdbc.dao;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.framework.orm.jdbc.JdbcMapper;
import com.rnkrsoft.opensource.iam.jdbc.bo.FetchAuthorityByAppAndUserBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.FetchAuthorityByAppBO;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryAuthorityBO;
import com.rnkrsoft.opensource.iam.jdbc.entity.AuthorityEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rnkrsoft.com on 2018/10/25.
 */
public interface AuthorityDAO extends JdbcMapper<AuthorityEntity, String> {
    List<FetchAuthorityByAppBO> fetchAuthorityByApp(@Param("appId") Integer appId);

    List<FetchAuthorityByAppAndUserBO> fetchAuthorityByAppAndUser(@Param("appCode") String appCode, @Param("userId") String userId);


    List<Integer> selectSubActionsByRole(@Param("roleId") Integer roleId);

    int fixByAppId(@Param("appId") Integer appId);

    /**
     * 查询权限资源
     *
     * @param pagination
     * @return
     */
    Pagination<QueryAuthorityBO> queryAuthority(Pagination<QueryAuthorityBO> pagination);
}

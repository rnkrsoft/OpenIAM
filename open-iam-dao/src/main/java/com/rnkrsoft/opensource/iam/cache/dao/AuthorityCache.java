package com.rnkrsoft.opensource.iam.cache.dao;

import com.rnkrsoft.framework.orm.cache.*;
import com.rnkrsoft.framework.orm.cache.Set;
import com.rnkrsoft.opensource.iam.cache.entity.UserAuthorityCacheEntity;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Cache
public interface AuthorityCache extends CacheInterface{
    @Set(expire = 60)
    void create(String userAndApp, UserAuthorityCacheEntity session);

    @Get
    UserAuthorityCacheEntity get(String userAndApp);

    @Remove
    void delete(String userAndApp);

    @Expire
    void expire(String userAndApp, Integer expireSecond);

    @Keys
    java.util.Set<String> keys(String keyword);
}

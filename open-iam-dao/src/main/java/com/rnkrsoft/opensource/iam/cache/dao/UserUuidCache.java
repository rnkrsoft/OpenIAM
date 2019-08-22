package com.rnkrsoft.opensource.iam.cache.dao;

import com.rnkrsoft.framework.orm.cache.*;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Cache
public interface UserUuidCache extends CacheInterface {
    @Set(expire = 2 * 60)
    void createUuid(String uuid, String token);

    @Expire
    void expireUuid(String uuid, Integer expireSecond);

    @Get
    String getUuid(String uuid);

    @Remove
    void remove(String uuid);
}

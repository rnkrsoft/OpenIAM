package com.rnkrsoft.opensource.iam.cache.dao;

import com.rnkrsoft.framework.orm.cache.*;
import com.rnkrsoft.opensource.iam.cache.entity.SessionEntity;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Cache
public interface SessionCache extends CacheInterface{
    @Set(expire = 10 * 60)
    void createSession(String token, SessionEntity session);

    @Set
    void createToken(String userIdAndChannel, String token);

    @Remove
    void deleteSession(String token);

    @Get
    SessionEntity getSession(String token);

    @Get
    String getToken(String userIdAndChannel);

    @Remove
    void deleteToken(String userIdAndChannel);

    @Expire
    void expireSession(String token, Integer expireSecond);
}

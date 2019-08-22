package com.rnkrsoft.opensource.iam.cache.dao;

import com.rnkrsoft.framework.orm.cache.*;
import com.rnkrsoft.opensource.iam.cache.entity.SmsCodeEntity;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Cache
public interface SmsCache extends CacheInterface{

    @Set(expire = 60)
    void createSmsCode(String mobileNo, SmsCodeEntity entity);

    @Expire
    void expireSmsCode(String mobileNo, Integer expireSecond);

    @Get
    SmsCodeEntity get(String mobileNo);

    @Remove
    void remove(String mobileNo);
}

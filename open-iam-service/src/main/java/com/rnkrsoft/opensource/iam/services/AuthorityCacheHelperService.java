package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.opensource.iam.cache.dao.AuthorityCache;
import com.rnkrsoft.opensource.iam.cache.entity.UserAuthorityCacheEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by woate on 2019/7/17.
 */
@Slf4j
@Service
public class AuthorityCacheHelperService {
    @Autowired
    AuthorityCache authorityCache;

    public UserAuthorityCacheEntity get(String userId, String appCode) {
        String key = userId + "@" + appCode;
        UserAuthorityCacheEntity entity = authorityCache.get(key);
        log.debug("get auth {} ,{}", key, entity);
        return entity;
    }

    public void store(String userId, String appCode, UserAuthorityCacheEntity entity) {
        String key = userId + "@" + appCode;
        log.debug("store auth {} ,{}", key, entity);
        authorityCache.create(key, entity);
        //10分钟，缓存清除
        authorityCache.expire(key, 10 * 60);
    }


    public void clear(String keyword) {
        Set<String> keys = authorityCache.keys(keyword);
        for (String key : keys) {
            log.debug("delete auth {}", key);
            authorityCache.delete(key);
        }
    }
}

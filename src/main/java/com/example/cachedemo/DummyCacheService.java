package com.example.cachedemo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by hazelcast on 7/12/17.
 */
@CacheConfig(cacheResolver = "prefixCacheResolver")
public class DummyCacheService {

    @Cacheable(value = "abc")
    public String getCityName(int plate) {
        System.out.println("getting city name for " + plate);
        return plate  + "";
    }
}

package com.imooc.o2o.service;

public interface CacheService {
    /**
     * 依据key前缀删除匹配该模式下的所有key-value
     */
    void removeFromCache(String keyPrefix);
}

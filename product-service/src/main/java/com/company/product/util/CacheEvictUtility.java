package com.company.product.util;



import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;


@UtilityClass
public class CacheEvictUtility {

    CacheManager cacheManager;

    public static void evictAllCaches() {
        cacheManager.getCacheNames()
                .forEach(
                        cacheName -> cacheManager
                                .getCache(
                                        cacheName
                                ).clear()
                );
    }

}

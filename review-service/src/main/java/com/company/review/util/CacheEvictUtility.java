package com.company.review.util;



import lombok.experimental.UtilityClass;
import org.springframework.cache.CacheManager;


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

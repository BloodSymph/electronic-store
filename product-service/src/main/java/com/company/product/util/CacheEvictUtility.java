package com.company.product.util;


import lombok.experimental.UtilityClass;
import org.springframework.cache.CacheManager;
import java.util.Objects;


@UtilityClass
public class CacheEvictUtility {
    private static CacheManager cacheManager;

    public static void evictAllCaches(){
        cacheManager.getCacheNames()
                .forEach(
                        cacheName -> Objects.requireNonNull(
                                cacheManager.getCache(cacheName)
                        ).clear()
                );
    }

}

package com.company.product.util;



import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;


@UtilityClass
public class CacheEvictUtil {
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

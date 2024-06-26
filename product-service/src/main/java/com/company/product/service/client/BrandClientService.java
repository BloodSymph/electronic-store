package com.company.product.service.client;

import com.company.product.dto.client.brand.BrandClientResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandClientService {

    List<BrandClientResponse> getAllBrands();

    List<BrandClientResponse> getBrandsByCategory(String categoryUrl);

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}

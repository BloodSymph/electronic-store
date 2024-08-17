package com.company.product.service.client.implementation;

import com.company.product.dto.client.brand.BrandClientResponse;
import com.company.product.entity.BrandEntity;
import com.company.product.mapper.client.BrandClientMapper;
import com.company.product.repository.BrandRepository;
import com.company.product.service.client.BrandClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.product.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"brands_client"})
public class BrandClientServiceImpl implements BrandClientService {

    private final BrandRepository brandRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public List<BrandClientResponse> getAllBrands() {
        List<BrandEntity> brands = brandRepository.findAll();
        return brands.stream()
                .map(BrandClientMapper::mapToBrandClientResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public List<BrandClientResponse> getBrandsByCategory(String categoryUrl) {
        List<BrandEntity> brands = brandRepository.getByCategories(categoryUrl);
        return brands.stream()
                .map(BrandClientMapper::mapToBrandClientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}

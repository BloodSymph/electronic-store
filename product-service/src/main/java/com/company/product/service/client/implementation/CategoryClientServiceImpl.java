package com.company.product.service.client.implementation;

import com.company.product.dto.client.category.CategoryClientResponse;
import com.company.product.entity.CategoryEntity;
import com.company.product.mapper.client.CategoryClientMapper;
import com.company.product.repository.CategoryRepository;
import com.company.product.service.client.CategoryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.product.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"categories"})
public class CategoryClientServiceImpl implements CategoryClientService {

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public List<CategoryClientResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryClientMapper::mapToCategoryClientResponse).
                collect(Collectors.toList());
    }

    @Override
    public List<CategoryClientResponse> getCategoriesByBrand(String brandUrl) {
        return categoryRepository.findCategoriesByBrand(brandUrl)
                .stream()
                .map(CategoryClientMapper::mapToCategoryClientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}

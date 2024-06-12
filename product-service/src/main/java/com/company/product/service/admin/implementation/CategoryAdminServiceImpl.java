package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.category.CategoryAdminRequest;
import com.company.product.dto.admin.category.CategoryAdminResponse;
import com.company.product.dto.admin.category.CategoryDetailedAdminResponse;
import com.company.product.entity.CategoryEntity;
import com.company.product.exception.exceptions.category.CategoryNotFoundException;
import com.company.product.exception.exceptions.category.CategoryVersionNotValidException;
import com.company.product.mapper.admin.CategoryAdminMapper;
import com.company.product.repository.CategoryRepository;
import com.company.product.service.admin.CategoryAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.product.mapper.admin.CategoryAdminMapper.*;
import static com.company.product.util.CacheEvictUtility.evictAllCaches;
import static com.company.product.util.URLGeneratorUtility.toUrlAddress;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"categories"})
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<CategoryAdminResponse> getAllCategories(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .map(CategoryAdminMapper::mapToCategoryAdminResponse);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<CategoryAdminResponse> searchCategories(
            Pageable pageable, String categoryName) {

        return categoryRepository
                .searchByNameIgnoreCase(pageable, categoryName)
                .map(CategoryAdminMapper::mapToCategoryAdminResponse);
    }

    @Override
    public CategoryDetailedAdminResponse getDetailsAboutCategory(
            String categoryUrl) {
        CategoryEntity category = categoryRepository
                .getDetailsAboutCategory(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not find category by current url: " + categoryUrl + " !"
                        )
                );
        return mapToCategoryDetailedAdminResponse(category);
    }

    @Override
    @CachePut(unless = "#result == null ")
    public CategoryAdminResponse createNewCategory(
            CategoryAdminRequest categoryAdminRequest) {
        CategoryEntity category = mapRequestToCategoryEntity(categoryAdminRequest);
        category.setUrl(toUrlAddress(categoryAdminRequest.getName()));
        categoryRepository.save(category);
        return mapToCategoryAdminResponse(category);
    }

    @Override
    @Transactional
    @CachePut(unless = "#result == null ")
    public CategoryAdminResponse updateCurrentCategory(
            CategoryAdminRequest categoryAdminRequest, String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not find category by current url: " + categoryUrl + " !"
                        )
                );

        if (!category.getVersion().equals(categoryAdminRequest.getVersion())) {
            throw new CategoryVersionNotValidException(
                    "Category Entity version: " + categoryAdminRequest.getVersion() + " not valid!"
            );
        }

        category = mapRequestToCategoryEntity(categoryAdminRequest);
        category.setUrl(toUrlAddress(categoryAdminRequest.getName()));
        categoryRepository.save(category);

        return mapToCategoryAdminResponse(category);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deleteCurrentCategory(
            String categoryUrl, Long categoryVersion) {
        if (!categoryRepository.existsByUrlIgnoreCase(categoryUrl)) {
            throw new CategoryNotFoundException(
                    "Can not find category by current url: " + categoryUrl + " !"
            );
        }
        if (!categoryRepository.existsByVersion(categoryVersion)) {
            throw new CategoryVersionNotValidException(
                    "Category Entity version: " + categoryVersion + " not valid!"
            );
        }
        categoryRepository.deleteByUrlIgnoreCase(categoryUrl);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}

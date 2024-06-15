package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.brand.BrandAdminRequest;
import com.company.product.dto.admin.brand.BrandAdminResponse;
import com.company.product.entity.BrandEntity;
import com.company.product.entity.CategoryEntity;
import com.company.product.exception.exceptions.brand.BrandNotFoundException;
import com.company.product.exception.exceptions.brand.BrandVersionNotValidException;
import com.company.product.exception.exceptions.category.CategoryNotFoundException;
import com.company.product.mapper.admin.BrandAdminMapper;
import com.company.product.repository.BrandRepository;
import com.company.product.repository.CategoryRepository;
import com.company.product.service.admin.BrandAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.product.mapper.admin.BrandAdminMapper.*;
import static com.company.product.util.CacheEvictUtility.evictAllCaches;
import static com.company.product.util.URLGeneratorUtility.toUrlAddress;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"brands"})
public class BrandAdminServiceImpl implements BrandAdminService {

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<BrandAdminResponse> getAllBrands(Pageable pageable) {
        return brandRepository
                .findAll(pageable)
                .map(BrandAdminMapper::mapToBrandAdminResponse);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<BrandAdminResponse> searchBrands(
            Pageable pageable, String brandName) {
        return brandRepository
                .searchByNameIgnoreCase(pageable, brandName)
                .map(BrandAdminMapper::mapToBrandAdminResponse);
    }

    @Override
    @Transactional
    @CachePut(unless = "#result == null ")
    public BrandAdminResponse createNewBrand(
            BrandAdminRequest brandAdminRequest) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(brandAdminRequest.getCategoryUrl())
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not find category by current url: "
                                        + brandAdminRequest.getCategoryUrl() + " !"
                        )
                );

        BrandEntity brand = mapToBrandEntityAdminRequest(brandAdminRequest);
        brand.setUrl(toUrlAddress(brandAdminRequest.getName()));
        category.getBrands().add(brand);
        brandRepository.save(brand);

        return mapToBrandAdminResponse(brand);

    }


    @Override
    @Transactional
    @CachePut(unless = "#result == null ")
    public BrandAdminResponse updateCurrentBrand(
            BrandAdminRequest brandAdminRequest, String brandUrl) {

        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not find brand by current url: " + brandUrl + " !"
                        )
                );

        if (!brand.getVersion().equals(brandAdminRequest.getVersion())) {
            throw new BrandVersionNotValidException(
                    "Brand Entity version: " + brandAdminRequest.getVersion() + " not valid!"
            );
        }

        brand.setName(brandAdminRequest.getName());
        brand.setUrl(toUrlAddress(brandAdminRequest.getName()));
        brandRepository.save(brand);

        return mapToBrandAdminResponse(brand);
    }


    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deleteCurrentBrand(String brandUrl, Long brandVersion) {
        if (!brandRepository.existsByUrlIgnoreCase(brandUrl)) {
            throw new BrandNotFoundException(
                    "Can not find brand by current url: " + brandUrl + " !"
            );
        }
        if (!brandRepository.existsByVersion(brandVersion)) {
            throw new BrandVersionNotValidException(
                    "Brand Entity version: " + brandVersion + " not valid!"
            );
        }
        brandRepository.deleteByUrlIgnoreCase(brandUrl);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}

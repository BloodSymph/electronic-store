package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.description.DescriptionAdminRequest;
import com.company.product.dto.admin.description.DescriptionAdminResponse;
import com.company.product.entity.DescriptionEntity;
import com.company.product.entity.ProductEntity;
import com.company.product.exception.exceptions.description.DescriptionNotFoundException;
import com.company.product.exception.exceptions.description.DescriptionVersionNotValidException;
import com.company.product.exception.exceptions.product.ProductNotFoundException;
import com.company.product.repository.DescriptionRepository;
import com.company.product.repository.ProductRepository;
import com.company.product.service.admin.DescriptionAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.product.mapper.admin.DescriptionAdminMapper.mapRequestToDescriptionEntity;
import static com.company.product.mapper.admin.DescriptionAdminMapper.mapToDescriptionAdminResponse;
import static com.company.product.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"descriptions_admin"})
public class DescriptionAdminImp implements DescriptionAdminService {

    private final DescriptionRepository descriptionRepository;

    private final ProductRepository productRepository;

    @Override
    @Transactional
    @CachePut(unless = "#result == null ")
    public DescriptionAdminResponse createProductDescription(
            DescriptionAdminRequest descriptionAdminRequest) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(descriptionAdminRequest.getProductUrl())
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not product by current url: " + descriptionAdminRequest.getProductUrl() + " !"
                        )
                );

        DescriptionEntity description;
        description = mapRequestToDescriptionEntity(descriptionAdminRequest);
        description.setProduct(product);
        descriptionRepository.save(description);

        return mapToDescriptionAdminResponse(description);
    }

    @Override
    @Transactional
    @CachePut(unless = "#result == null ")
    public DescriptionAdminResponse updateProductDescription(
            DescriptionAdminRequest descriptionAdminRequest,
            String productUrl) {

        DescriptionEntity description = descriptionRepository
                .findByProduct_Url(productUrl)
                .orElseThrow(
                        () -> new DescriptionNotFoundException(
                                "Can not description by product url: " + productUrl + " !"
                        )
                );

        if (!description.getVersion().equals(descriptionAdminRequest.getVersion())) {
            throw new DescriptionVersionNotValidException(
                    "Description Entity version: " + descriptionAdminRequest.getVersion() + " not valid!"
            );
        }

        description.setDescription(descriptionAdminRequest.getDescription());
        descriptionRepository.save(description);

        return mapToDescriptionAdminResponse(description);
    }

    @Override
    @Transactional
    public void deleteCurrentDescription(
            String productUrl, Long descriptionVersion) {
        if (!descriptionRepository.existsByProduct_Url(productUrl)) {
            throw new DescriptionNotFoundException(
                    "Can not description by product url: " + productUrl + " !"
            );
        }
        if (!descriptionRepository.existsByVersion(descriptionVersion)) {
            throw new DescriptionVersionNotValidException(
                    "Description Entity version: " + descriptionVersion + " not valid!"
            );
        }
        descriptionRepository.deleteByProduct_Url(productUrl);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}

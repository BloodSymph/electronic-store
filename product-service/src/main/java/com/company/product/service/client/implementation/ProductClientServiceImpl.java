package com.company.product.service.client.implementation;

import com.company.product.dto.client.product.ProductClientResponse;
import com.company.product.dto.client.product.ProductDetailedClientResponse;
import com.company.product.entity.ProductEntity;
import com.company.product.exception.exceptions.file.SearchingFileDirectoryException;
import com.company.product.exception.exceptions.product.ProductNotFoundException;
import com.company.product.mapper.client.ProductClientMapper;
import com.company.product.repository.ProductRepository;
import com.company.product.service.client.ProductClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.company.product.mapper.client.ProductClientMapper.mapToProductClientResponse;
import static com.company.product.mapper.client.ProductClientMapper.mapToProductDetailedClientResponse;
import static com.company.product.util.CacheEvictUtility.evictAllCaches;
import static com.company.product.util.FileUtility.encodeFile;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"products"})
public class ProductClientServiceImpl implements ProductClientService {

    private final ProductRepository productRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ProductClientResponse> getAllProducts(Pageable pageable)  {
        Page<ProductEntity> products = productRepository.findAll(pageable);
        products.forEach(product -> {
            try {
                product.setPhoto(encodeFile(product.getPhoto()));
            } catch (IOException e) {
                throw new SearchingFileDirectoryException(
                        "Can not find file by source directory: " + product.getPhoto() + " !"
                );
            }
        });
        return products.map(ProductClientMapper::mapToProductClientResponse);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ProductClientResponse> searchProducts(
            Pageable pageable, String searchText) {
        Page<ProductEntity> products = productRepository.searchProducts(pageable, searchText);
        products.forEach(product -> {
            try {
                product.setPhoto(encodeFile(product.getPhoto()));
            } catch (IOException e) {
                throw new SearchingFileDirectoryException(
                        "Can not find file by source directory: " + product.getPhoto() + " !"
                );
            }
        });
        return products.map(ProductClientMapper::mapToProductClientResponse);
    }

    @Override
    public ProductDetailedClientResponse getDetailsAboutProduct(
            String productUrl) throws IOException {
        ProductEntity product = productRepository
                .getDetailsAboutProduct(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not find product by current url: " + productUrl + " !"
                        )
                );

        product.setPhoto(encodeFile(product.getPhoto()));

        return mapToProductDetailedClientResponse(product);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ProductClientResponse> getProductsByCategory(
            Pageable pageable, String categoryUrl) {
        Page<ProductEntity> products = productRepository.findByCategory(pageable, categoryUrl);
        products.forEach(product -> {
            try {
                product.setPhoto(encodeFile(product.getPhoto()));
            } catch (IOException e) {
                throw new SearchingFileDirectoryException(
                        "Can not find file by source directory: " + product.getPhoto() + " !"
                );
            }
        });
        return products.map(ProductClientMapper::mapToProductClientResponse);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ProductClientResponse> getProductsByBrand(
            Pageable pageable, String brandUrl) {
        Page<ProductEntity> products = productRepository.findByBrand(pageable, brandUrl);
        products.forEach(product -> {
            try {
                product.setPhoto(encodeFile(product.getPhoto()));
            } catch (IOException e) {
                throw new SearchingFileDirectoryException(
                        "Can not find file by source directory: " + product.getPhoto() + " !"
                );
            }
        });
        return products.map(ProductClientMapper::mapToProductClientResponse);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}

package com.company.product.service.client.implementation;

import com.company.product.dto.client.product.ProductClientResponse;
import com.company.product.dto.client.product.ProductDetailedClientResponse;
import com.company.product.repository.ProductRepository;
import com.company.product.service.client.ProductClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.company.product.util.CacheEvictUtil.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"products"})
public class ProductClientServiceImpl implements ProductClientService {

    private final ProductRepository productRepository;

    @Value("${config.location}")
    private static String filePath;

    @Override
    public Page<ProductClientResponse> getAllProducts(Pageable pageable)  {
        return null;
    }

    @Override
    public Page<ProductClientResponse> searchProducts(Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public ProductDetailedClientResponse getDetailsAboutProduct(String productUrl) {
        return null;
    }

    @Override
    public Page<ProductClientResponse> getProductsByCategory(Pageable pageable, String categoryUrl) {
        return null;
    }

    @Override
    public Page<ProductClientResponse> getProductsByBrand(Pageable pageable, String brandUrl) {
        return null;
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}

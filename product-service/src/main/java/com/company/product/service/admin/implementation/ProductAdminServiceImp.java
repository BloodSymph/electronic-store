package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.product.ProductAdminRequest;
import com.company.product.dto.admin.product.ProductAdminResponse;
import com.company.product.dto.admin.product.ProductDetailedAdminResponse;
import com.company.product.repository.ProductRepository;
import com.company.product.service.admin.ProductAdminService;
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
public class ProductAdminServiceImp implements ProductAdminService {

    private final ProductRepository productRepository;

    @Value("${config.location}")
    private static String filePath;

    @Override
    public Page<ProductAdminResponse> getAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductAdminResponse> searchProducts(Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public ProductDetailedAdminResponse getDetailsAboutProduct(String productUrl) {
        return null;
    }

    @Override
    public ProductAdminResponse createNewProduct(ProductAdminRequest productAdminRequest) {
        return null;
    }

    @Override
    public ProductAdminResponse updateCurrentProduct(ProductAdminRequest productAdminRequest, String productUrl) {
        return null;
    }

    @Override
    public void deleteCurrentProduct(String productUrl, Long productVersion) {

    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}

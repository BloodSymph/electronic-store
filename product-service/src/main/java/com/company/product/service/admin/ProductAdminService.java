package com.company.product.service.admin;

import com.company.product.dto.admin.product.ProductAdminRequest;
import com.company.product.dto.admin.product.ProductAdminResponse;
import com.company.product.dto.admin.product.ProductDetailedAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface ProductAdminService {

    Page<ProductAdminResponse> getAllProducts(Pageable pageable);

    Page<ProductAdminResponse> searchProducts(Pageable pageable, String searchText);

    ProductDetailedAdminResponse getDetailsAboutProduct(String productUrl);

    ProductAdminResponse createNewProduct(ProductAdminRequest productAdminRequest);

    ProductAdminResponse updateCurrentProduct(
            ProductAdminRequest productAdminRequest, String productUrl
    );

    void deleteCurrentProduct(String productUrl, Long productVersion);

    @Scheduled(fixedRate = 6000)
    void evictAllCacheWithTime();

}
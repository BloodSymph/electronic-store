package com.company.product.service.admin;

import com.company.product.dto.admin.file.FileAdminRequest;
import com.company.product.dto.admin.product.ProductAdminRequest;
import com.company.product.dto.admin.product.ProductAdminResponse;
import com.company.product.dto.admin.product.ProductDetailedAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public interface ProductAdminService {

    Page<ProductAdminResponse> getAllProducts(Pageable pageable);

    Page<ProductAdminResponse> searchProducts(
            Pageable pageable, String searchText
    );

    ProductDetailedAdminResponse getDetailsAboutProduct(
            String productUrl) throws IOException;

    ProductAdminResponse createNewProduct(
            ProductAdminRequest productAdminRequest
    );

    CompletableFuture<ProductAdminResponse> createPhotoForProduct(
            FileAdminRequest fileAdminRequest, String productUrl
    ) throws IOException;

    CompletableFuture<ProductAdminResponse> deletePhotoForProduct(String productUrl) throws IOException;

    ProductAdminResponse updateCurrentProduct(
            ProductAdminRequest productAdminRequest, String productUrl
    );

    void deleteCurrentProduct(
            String productUrl, Long productVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}

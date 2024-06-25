package com.company.product.service.client;

import com.company.product.dto.client.product.ProductClientResponse;
import com.company.product.dto.client.product.ProductDetailedClientResponse;
import com.company.product.dto.feign.ProductFeignClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductClientService {

    Page<ProductClientResponse> getAllProducts(Pageable pageable);

    Page<ProductClientResponse> searchProducts(
            Pageable pageable, String searchText
    );

    ProductDetailedClientResponse getDetailsAboutProduct(
            String productUrl) throws IOException;

    ProductFeignClientDto getProductForCart(Long productId);

    Page<ProductClientResponse> getProductsByCategory(
            Pageable pageable, String categoryUrl
    );

    Page<ProductClientResponse> getProductsByBrand(
            Pageable pageable, String brandUrl
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}

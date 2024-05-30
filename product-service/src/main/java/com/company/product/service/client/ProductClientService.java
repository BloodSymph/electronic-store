package com.company.product.service.client;

import com.company.product.dto.client.product.ProductClientResponse;
import com.company.product.dto.client.product.ProductDetailedClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductClientService {

    Page<ProductClientResponse> getAllProducts(Pageable pageable);

    Page<ProductClientResponse> searchProducts(Pageable pageable, String searchText);

    ProductDetailedClientResponse getDetailsAboutProduct(String productUrl);

    Page<ProductClientResponse> getProductsByCategory(Pageable pageable, String categoryUrl);

    Page<ProductClientResponse> getProductsByBrand(Pageable pageable, String brandUrl);

}

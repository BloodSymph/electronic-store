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

    List<ProductClientResponse> searchProducts(String searchText);

    ProductDetailedClientResponse getDetailsAboutProduct(String productUrl);

    String getProductPhotoByProductUrl(String productUrl);

    List<ProductClientResponse> getProductsByCategory(String categoryUrl);

    List<ProductClientResponse> getProductsByBrand(String brandUrl);

}

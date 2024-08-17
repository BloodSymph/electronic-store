package com.company.product.controller.client;

import com.company.product.dto.client.product.ProductClientResponse;
import com.company.product.dto.client.product.ProductDetailedClientResponse;
import com.company.product.dto.feign.ProductFeignClientDto;
import com.company.product.service.client.ProductClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/client")
public class ProductClientController {

    private final ProductClientService productClientService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductClientResponse> getAllProducts(
           @PageableDefault(
                   sort = "title",
                   direction = Sort.Direction.DESC,
                   page = 0,
                   size = 15
           ) Pageable pageable) {
        return productClientService.getAllProducts(pageable);
    }

    @GetMapping("/products/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductClientResponse> searchProducts(
            @RequestParam(value = "searchText") String searchText,
            @PageableDefault(
                    sort = "title",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 15
            ) Pageable pageable) {
        return productClientService.searchProducts(
                pageable, searchText
        );
    }

    @GetMapping("/products/{productUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetailedClientResponse getProductDetails(
            @PathVariable(value = "productUrl") String productUrl) throws IOException {
        return productClientService.getDetailsAboutProduct(productUrl);
    }

    @GetMapping("/products/{productId}/cart/get")
    @ResponseStatus(HttpStatus.OK)
    public ProductFeignClientDto getProductForCart(
            @PathVariable(value = "productId") Long productId) {
        return productClientService.getProductForCart(productId);
    }

    @GetMapping("/products/{productUrl}/get-title")
    @ResponseStatus(HttpStatus.OK)
    public String getProductIdForReviewByUrl(
            @PathVariable(value = "productUrl") String productUrl) {
        return productClientService.getProductTitleForReviewService(productUrl);
    }

    @GetMapping("/categories/{categoryUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductClientResponse> getProductByCategory(
            @PathVariable(value = "categoryUrl") String categoryUrl,
            @PageableDefault(
                    sort = "title",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 15
            ) Pageable pageable) {
        return productClientService.getProductsByCategory(
                pageable, categoryUrl
        );
    }

    @GetMapping("/brands/{brandUrl}/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductClientResponse> getProductsByBrand(
            @PathVariable(value = "brandUrl") String brandUrl,
            @PageableDefault(
                    sort = "title",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 15
            ) Pageable pageable){
       return  productClientService.getProductsByBrand(
               pageable, brandUrl
       );
    }

}

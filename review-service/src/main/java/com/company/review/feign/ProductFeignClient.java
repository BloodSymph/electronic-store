package com.company.review.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;


@FeignClient(
        name = "product-client",
        url = "http://localhost:8081/api/v1/product-service/client/products"
)
public interface ProductFeignClient {

    @GetMapping("/{productUrl}/get-title")
    @ResponseStatus(HttpStatus.OK)
    String getProductTitleForReview(
            @PathVariable(value = "productUrl") String productUrl
    );

}

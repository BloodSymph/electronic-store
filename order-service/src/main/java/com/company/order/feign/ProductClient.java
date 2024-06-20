package com.company.order.feign;

import com.company.order.dto.feign.ProductFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "order",
        url = "/api/v1/product-service/client/**"
)
public interface ProductClient {

    @GetMapping("/products/{productId}/cart/get")
    ProductFeignResponse getProductForCart(@PathVariable("productId") Long productId);

}

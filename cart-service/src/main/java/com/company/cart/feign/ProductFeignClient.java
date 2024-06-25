package com.company.cart.feign;

import com.company.cart.dto.feign.ItemFeignClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "product-client",
        url = "/api/v1/product-service/client/products"
)
public interface ProductFeignClient {

    @GetMapping("/{productId}/cart/get")
    @ResponseStatus(HttpStatus.OK)
    ItemFeignClientDto getProductForCart(
            @PathVariable(value = "productId") Long productId
    );

}

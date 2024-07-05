package com.company.order.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "cart-client",
        url = "http://localhost:8082/api/v1/cart-service/client"
)
public interface CartFeignClient {
}

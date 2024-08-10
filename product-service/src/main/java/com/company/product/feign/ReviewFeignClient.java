package com.company.product.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "review-client",
        url = "http://localhost:8084/api/v1/review-service/client"
)
public interface ReviewFeignClient {



}

package com.company.order.feign;

import org.springframework.cloud.openfeign.FeignClient;




@FeignClient(name = "order", url = "/api/v1/product-service/admin/**")
public interface ProductClient {



}

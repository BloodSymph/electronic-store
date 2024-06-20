package com.company.order.feign;

import com.company.order.dto.client.ProductClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order", url = "/api/v1/product-service/admin/**")
public interface ProductClient {



}

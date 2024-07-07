package com.company.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "cart-client",
        url = "http://localhost:8082/api/v1/cart-service/client"
)
public interface CartFeignClient {

    @GetMapping("/{profileId}/cart/calculate/price")
    @ResponseStatus(HttpStatus.OK)
    Double itemsPrice(@PathVariable(value = "profileId") Long profileId);

    @GetMapping("/{profileId}/cart/get/id")
    @ResponseStatus(HttpStatus.OK)
    Long getCartIdForOrder(@PathVariable(value = "profileId") Long profileId);

}

package com.company.cart.controller.client;

import com.company.cart.service.client.CartClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-service/client")
public class CartClientController {

    private final CartClientService cartClientService;

}

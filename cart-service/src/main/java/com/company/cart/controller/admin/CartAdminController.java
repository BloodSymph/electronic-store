package com.company.cart.controller.admin;

import com.company.cart.service.admin.CartAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-service/admin")
public class CartAdminController {

    private final CartAdminService cartAdminService;

}

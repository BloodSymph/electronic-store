package com.company.product.controller.admin;

import com.company.product.service.admin.BrandAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-service/admin")
@RequiredArgsConstructor
public class BrandAdminController {

    private final BrandAdminService brandAdminService;



}

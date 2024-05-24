package com.company.product.controller.client;

import com.company.product.dto.client.brand.BrandClientResponse;
import com.company.product.service.client.BrandClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-service/client")
@RequiredArgsConstructor
public class BrandClientController {

    private final BrandClientService brandClientService;

    @GetMapping("/brands")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandClientResponse> getAllBrands() {
        return brandClientService.getAllBrands();
    }

    @GetMapping("categories/{categoryUrl}/brands")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandClientResponse> getBrandsByCategories(
            @PathVariable(value = "categoryUrl") String categoryUrl) {

        return brandClientService.getBrandsByCategory(categoryUrl);

    }

}

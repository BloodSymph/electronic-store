package com.company.product.controller.client;

import com.company.product.dto.client.category.CategoryClientResponse;
import com.company.product.service.client.CategoryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-service/client")
@RequiredArgsConstructor
public class CategoryClientController {

    private final CategoryClientService categoryClientService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryClientResponse> getListOfCategories() {
        return categoryClientService.getAllCategories();
    }

    @GetMapping("/brands/{brandUrl}/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryClientResponse> getCategoriesByBrand(
            @PathVariable(value = "brandUrl") String brandUrl) {
        return categoryClientService.getCategoriesByBrand(brandUrl);
    }

}

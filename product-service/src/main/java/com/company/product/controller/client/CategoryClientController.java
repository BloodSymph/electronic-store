package com.company.product.controller.client;

import com.company.product.dto.client.category.CategoryClientResponse;
import com.company.product.service.client.CategoryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-service/client")
@RequiredArgsConstructor
public class CategoryClientController {

    private final CategoryClientService categoryClientService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryClientResponse> getListOfCategories() {
        return categoryClientService.getAllCategories();
    }

}

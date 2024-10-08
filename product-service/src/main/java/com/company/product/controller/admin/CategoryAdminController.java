package com.company.product.controller.admin;

import com.company.product.dto.admin.category.CategoryAdminRequest;
import com.company.product.dto.admin.category.CategoryAdminResponse;
import com.company.product.service.admin.CategoryAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-service/admin")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryAdminService categoryAdminService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryAdminResponse> getListOfCategories(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable) {

        return categoryAdminService.getAllCategories(pageable);

    }

    @GetMapping("/categories/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryAdminResponse> searchCategory(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable,
            @RequestParam(
                    value = "categoryName",
                    required = false,
                    defaultValue = "") String categoryName) {
        return categoryAdminService.searchCategories(
                pageable, categoryName
        );
    }

    @PostMapping("/categories/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryAdminResponse createNewCategory(
            @Valid @RequestBody CategoryAdminRequest categoryAdminRequest) {
        return categoryAdminService.createNewCategory(categoryAdminRequest);
    }

    @PutMapping("/categories/{categoryUrl}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryAdminResponse updateCurrentCategory(
            @Valid @RequestBody CategoryAdminRequest categoryAdminRequest,
            @PathVariable(value = "categoryUrl") String categoryUrl) {

        return categoryAdminService.updateCurrentCategory(
                categoryAdminRequest, categoryUrl
        );
    }

    @DeleteMapping("/categories/{categoryUrl}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCurrentCategory(
            @PathVariable("categoryUrl") String categoryUrl,
            @RequestParam(value = "categoryVersion") Long categoryVersion) {

        categoryAdminService.deleteCurrentCategory(
                categoryUrl, categoryVersion
        );

        return new ResponseEntity<>(
                "Category successful deleted by url: " + categoryUrl + " !",
                HttpStatus.OK
        );
    }

}

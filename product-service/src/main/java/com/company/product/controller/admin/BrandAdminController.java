package com.company.product.controller.admin;

import com.company.product.dto.admin.brand.BrandAdminRequest;
import com.company.product.dto.admin.brand.BrandAdminResponse;
import com.company.product.service.admin.BrandAdminService;
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
public class BrandAdminController {

    private final BrandAdminService brandAdminService;

    @GetMapping("/brands")
    @ResponseStatus(HttpStatus.OK)
    public Page<BrandAdminResponse> getListOfBrands(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable) {

        return brandAdminService.getAllBrands(pageable);

    }

    @GetMapping("/brands/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<BrandAdminResponse> searchBrands(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable,
            @RequestParam(
                    value = "brandName",
                    required = false,
                    defaultValue = "") String brandName) {

        return brandAdminService.searchBrands(
                pageable, brandName
        );

    }

    @PostMapping("/brands/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BrandAdminResponse createNewBrand(
            @Valid @RequestBody BrandAdminRequest brandAdminRequest) {

        return brandAdminService.createNewBrand(brandAdminRequest);

    }

    @PutMapping("/brands/{brandUrl}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public BrandAdminResponse updateCurrentBrand(
            @Valid @RequestBody BrandAdminRequest brandAdminRequest,
            @PathVariable(value = "brandUrl") String brandUrl) {

        return brandAdminService.updateCurrentBrand(
                brandAdminRequest, brandUrl
        );

    }

    @DeleteMapping("/brands/{brandUrl}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCurrentBrand(
            @PathVariable(value = "brandUrl") String brandUrl,
            @RequestParam(value = "brandVersion") Long brandVersion) {

        brandAdminService.deleteCurrentBrand(
                brandUrl, brandVersion
        );
        return new ResponseEntity<>(
                "Brand successful deleted by url: " + brandUrl + " !",
                HttpStatus.OK
        );

    }

}

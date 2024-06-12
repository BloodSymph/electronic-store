package com.company.product.controller.admin;

import com.company.product.dto.admin.description.DescriptionAdminRequest;
import com.company.product.dto.admin.description.DescriptionAdminResponse;
import com.company.product.service.admin.DescriptionAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/admin")
public class DescriptionAdminController {

    private final DescriptionAdminService descriptionAdminService;

    @PostMapping("/product/description/create")
    @ResponseStatus(HttpStatus.CREATED)
    public DescriptionAdminResponse createDescription(
            @Valid @RequestBody DescriptionAdminRequest descriptionAdminRequest) {
        return descriptionAdminService.createProductDescription(descriptionAdminRequest);
    }

    @PutMapping("/products/{productUrl}/description/update")
    @ResponseStatus(HttpStatus.CREATED)
    public DescriptionAdminResponse updateDescriptionForProduct(
            @PathVariable(value = "productUrl") String productUrl,
            @Valid @RequestBody DescriptionAdminRequest descriptionAdminRequest) {
        return descriptionAdminService.updateProductDescription(
                descriptionAdminRequest, productUrl);
    }

    @DeleteMapping("/products/{productUrl}/description/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteDescriptionForProduct(
            @PathVariable(value = "productUrl") String productUrl,
            @RequestParam(value = "descriptionVersion") Long descriptionVersion) {
        descriptionAdminService.deleteCurrentDescription(productUrl, descriptionVersion);
        return new ResponseEntity<>(
                "Description successful deleted by product url: " + productUrl + " !",
                HttpStatus.OK
        );
    }

}

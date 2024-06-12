package com.company.product.controller.admin;

import com.company.product.dto.admin.discount.DiscountAdminRequest;
import com.company.product.dto.admin.discount.DiscountAdminResponse;
import com.company.product.service.admin.DiscountAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/admin")
public class DiscountAdminController {

    private final DiscountAdminService discountAdminService;

    @PostMapping("/product/discount/create")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountAdminResponse createDiscountForProduct(
            @Valid @RequestBody DiscountAdminRequest discountAdminRequest) {
        return discountAdminService.createDiscountForProduct(discountAdminRequest);
    }

    @PutMapping("/products/{productUrl}/discount/update")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountAdminResponse updateDiscountForProduct(
            @PathVariable(value = "productUrl") String productUrl,
            @Valid @RequestBody DiscountAdminRequest discountAdminRequest) {
        return discountAdminService.updateDiscountForProduct(
                discountAdminRequest, productUrl);
    }

    @DeleteMapping("/products/{productUrl}/discount/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteDiscountForProduct(
            @PathVariable(value = "productUrl") String productUrl,
            @RequestParam(value = "discountVersion") Long discountVersion) {
        discountAdminService.deleteCurrentDiscount(productUrl, discountVersion);
        return new ResponseEntity<>(
                "Discount successful deleted by product url: " + productUrl + " !",
                HttpStatus.OK
        );
    }

}

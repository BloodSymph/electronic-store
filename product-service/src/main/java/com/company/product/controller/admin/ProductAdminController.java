package com.company.product.controller.admin;

import com.company.product.dto.admin.file.FileAdminRequest;
import com.company.product.dto.admin.product.ProductAdminRequest;
import com.company.product.dto.admin.product.ProductAdminResponse;
import com.company.product.dto.admin.product.ProductDetailedAdminResponse;
import com.company.product.service.admin.ProductAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/admin")
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductAdminResponse> getAllProducts(
            @PageableDefault(
                    sort = "title",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15
            ) Pageable pageable) {
        return productAdminService.getAllProducts(pageable);
    }

    @GetMapping("/products/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductAdminResponse> searchProducts(
            @RequestParam(
                    value = "searchText",
                    required = false,
                    defaultValue = ""
            ) String searchText,
            @PageableDefault(
                    sort = "title",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15
            ) Pageable pageable) {
        return productAdminService.searchProducts(
                pageable, searchText
        );
    }

    //todo:Test method!
    @GetMapping("/products/{productUrl}/details")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetailedAdminResponse getProductDetails(
            @PathVariable(value = "productUrl") String productUrl) throws IOException {
        return productAdminService.getDetailsAboutProduct(productUrl);
    }

    @PostMapping("/products/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAdminResponse createNewProduct(
            @Valid @RequestBody ProductAdminRequest productAdminRequest) {
        return productAdminService.createNewProduct(productAdminRequest);
    }

    @PostMapping("/products/{productUrl}/photo/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ProductAdminResponse> createPhotoForProduct(
            @PathVariable(value = "productUrl") String productUrl,
            @Valid @RequestBody FileAdminRequest fileAdminRequest) throws IOException {
        return productAdminService.createPhotoForProduct(
                fileAdminRequest, productUrl
        );
    }

    @DeleteMapping("/products/{productUrl}/photo/delete")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<ProductAdminResponse> deleteFileForProduct(
            @PathVariable(value = "productUrl") String productUrl) throws IOException {
        return productAdminService.deletePhotoForProduct(productUrl);
    }

    @PutMapping("/products/{productUrl}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAdminResponse updateCurrentProduct(
            @PathVariable(value = "productUrl") String productUrl,
            @Valid @RequestBody ProductAdminRequest productAdminRequest) {
        return productAdminService.updateCurrentProduct(
                productAdminRequest, productUrl
        );
    }

    @DeleteMapping("/products/{productUrl}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCurrentProduct(
            @PathVariable(value = "productUrl") String productUrl,
            @RequestParam(value = "productVersion") Long productVersion) {
        productAdminService.deleteCurrentProduct(productUrl, productVersion);
        return new ResponseEntity<>(
                "Product successful deleted by product url: " + productUrl + " !",
                HttpStatus.OK
        );
    }

}

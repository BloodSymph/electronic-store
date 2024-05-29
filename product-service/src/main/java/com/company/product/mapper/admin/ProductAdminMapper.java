package com.company.product.mapper.admin;


import com.company.product.dto.admin.product.ProductAdminRequest;
import com.company.product.dto.admin.product.ProductAdminResponse;
import com.company.product.dto.admin.product.ProductDetailedAdminResponse;
import com.company.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

import static com.company.product.mapper.admin.BrandAdminMapper.mapToBrandAdminResponse;
import static com.company.product.mapper.admin.CategoryAdminMapper.mapToCategoryAdminResponse;
import static com.company.product.mapper.admin.DescriptionAdminMapper.mapToDescriptionAdminResponse;
import static com.company.product.mapper.admin.DiscountAdminMapper.mapToDiscountAdminResponse;

@Component
public class ProductAdminMapper {

    public static ProductAdminResponse mapToProductAdminResponse(ProductEntity product) {
        return ProductAdminResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .url(product.getUrl())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .created(product.getCreated())
                .updated(product.getUpdated())
                .version(product.getVersion())
                .build();
    }

    public static ProductEntity mapRequestToProductEntity(ProductAdminRequest productAdminRequest) {
        return ProductEntity.builder()
                .title(productAdminRequest.getTitle())
                .url(productAdminRequest.getUrl())
                .price(productAdminRequest.getPrice())
                .photo(productAdminRequest.getPhoto())
                .version(productAdminRequest.getVersion())
                .build();
    }

    public static ProductDetailedAdminResponse mapToProductDetailedAdminResponse(
            ProductEntity product) {
        return ProductDetailedAdminResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .url(product.getUrl())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .created(product.getCreated())
                .updated(product.getUpdated())
                .version(product.getVersion())
                .category(
                        mapToCategoryAdminResponse(
                                product.getCategory()
                        )
                )
                .brand(
                        mapToBrandAdminResponse(
                                product.getBrand()
                        )
                )
                .description(
                        mapToDescriptionAdminResponse(
                                product.getDescription()
                        )
                )
                .discount(
                        mapToDiscountAdminResponse(
                                product.getDiscount()
                        )
                )
                .build();
    }

}

package com.company.product.mapper.client;

import com.company.product.dto.client.product.ProductClientResponse;
import com.company.product.dto.client.product.ProductDetailedClientResponse;
import com.company.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

import static com.company.product.mapper.client.DescriptionClientMapper.mapToDescriptionClientResponse;
import static com.company.product.mapper.client.DiscountClientMapper.mapToDiscountClientResponse;

@Component
public class ProductClientMapper {

    public static ProductClientResponse mapToProductClientResponse(ProductEntity product) {
        return ProductClientResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .url(product.getUrl())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .discount(
                        mapToDiscountClientResponse(
                                product.getDiscount()
                        )
                )
                .build();
    }

    public static ProductDetailedClientResponse mapToProductDetailedClientResponse(
            ProductEntity product) {

        return ProductDetailedClientResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .url(product.getUrl())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .description(
                        mapToDescriptionClientResponse(
                                product.getDescription()
                        )
                )
                .discount(
                        mapToDiscountClientResponse(
                                product.getDiscount()
                        )
                )
                .build();

    }

}

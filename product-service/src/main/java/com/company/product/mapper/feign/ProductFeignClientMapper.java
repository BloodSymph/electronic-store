package com.company.product.mapper.feign;

import com.company.product.dto.feign.ProductFeignClientDto;
import com.company.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientMapper {

    public static ProductFeignClientDto mapToProductFeignClientDto(ProductEntity product) {
        return ProductFeignClientDto.builder()
                .productId(product.getId())
                .price(product.getPrice())
                .build();
    }


}

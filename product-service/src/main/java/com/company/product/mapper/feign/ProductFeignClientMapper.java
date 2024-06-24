package com.company.product.mapper.feign;

import com.company.product.dto.feign.ProductFeignClientDto;
import com.company.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientMapper {

    public static ProductFeignClientDto mapToProductFeignClientDto(ProductEntity product) {
        return ProductFeignClientDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .url(product.getUrl())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .version(product.getVersion())
                .build();
    }


}

package com.company.product.mapper.client;

import com.company.product.dto.client.brand.BrandClientResponse;
import com.company.product.entity.BrandEntity;
import org.springframework.stereotype.Component;

@Component
public class BrandClientMapper {

    public static BrandClientResponse mapToBrandClientResponse(BrandEntity brand) {
        return BrandClientResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .url(brand.getUrl())
                .build();
    }

}

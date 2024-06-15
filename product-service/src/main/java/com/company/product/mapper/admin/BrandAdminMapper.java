package com.company.product.mapper.admin;

import com.company.product.dto.admin.brand.BrandAdminRequest;
import com.company.product.dto.admin.brand.BrandAdminResponse;
import com.company.product.entity.BrandEntity;
import org.springframework.stereotype.Component;

@Component
public class BrandAdminMapper {

    public static BrandAdminResponse mapToBrandAdminResponse(BrandEntity brand) {
        return BrandAdminResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .url(brand.getUrl())
                .created(brand.getCreated())
                .updated(brand.getUpdated())
                .version(brand.getVersion())
                .build();
    }

    public static BrandEntity mapToBrandEntityAdminRequest(BrandAdminRequest adminRequest) {
        return BrandEntity.builder()
                .name(adminRequest.getName())
                .url(adminRequest.getUrl())
                .version(adminRequest.getVersion())
                .build();
    }

}

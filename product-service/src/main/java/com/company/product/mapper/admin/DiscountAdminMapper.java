package com.company.product.mapper.admin;

import com.company.product.dto.admin.discount.DiscountAdminRequest;
import com.company.product.dto.admin.discount.DiscountAdminResponse;
import com.company.product.entity.DiscountEntity;
import org.springframework.stereotype.Component;

@Component
public class DiscountAdminMapper {

    public static DiscountAdminResponse mapToDiscountAdminResponse(
            DiscountEntity discount) {
        return DiscountAdminResponse.builder()
                .id(discount.getId())
                .discount(discount.getDiscount())
                .created(discount.getCreated())
                .updated(discount.getUpdated())
                .version(discount.getVersion())
                .build();
    }

    public static DiscountEntity mapRequestToDiscountEntity(
            DiscountAdminRequest discountAdminRequest) {
        return DiscountEntity.builder()
                .discount(discountAdminRequest.getDiscount())
                .version(discountAdminRequest.getVersion())
                .build();
    }

}

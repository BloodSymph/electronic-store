package com.company.product.mapper.client;

import com.company.product.dto.client.discount.DiscountClientResponse;
import com.company.product.entity.DiscountEntity;
import org.springframework.stereotype.Component;

@Component
public class DiscountClientMapper {

    public static DiscountClientResponse mapToDiscountClientResponse(
            DiscountEntity discount) {
        return DiscountClientResponse.builder()
                .id(discount.getId())
                .discount(discount.getDiscount())
                .build();
    }

}

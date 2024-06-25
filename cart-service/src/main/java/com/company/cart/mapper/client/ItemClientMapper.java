package com.company.cart.mapper.client;

import com.company.cart.dto.client.item.ItemClientResponse;
import com.company.cart.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemClientMapper {

    public static ItemClientResponse mapToItemClientResponse(ItemEntity item) {
        return ItemClientResponse.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .price(item.getPrice())
                .build();
    }

}

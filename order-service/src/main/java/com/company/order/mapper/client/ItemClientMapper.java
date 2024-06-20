package com.company.order.mapper.client;

import com.company.order.dto.client.CartClientResponse;
import com.company.order.dto.client.ItemClientResponse;
import com.company.order.entity.CartEntity;
import com.company.order.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemClientMapper {

    public static ItemClientResponse mapToCartClientResponse(ItemEntity item) {
        return ItemClientResponse.builder()
                .id(item.getId())
                .productId(item.getId())
                .build();
    }

}

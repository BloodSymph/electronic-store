package com.company.order.mapper.client;

import com.company.order.dto.client.ordered.OrderedItemsClientRequest;
import com.company.order.dto.client.ordered.OrderedItemsClientResponse;
import com.company.order.entity.OrderedItemsEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderedItemsClientMapper {

    public static OrderedItemsClientResponse mapToOrderedItemsClientResponse(
            OrderedItemsEntity items) {
        return OrderedItemsClientResponse.builder()
                .id(items.getId())
                .cartId(items.getCartId())
                .orderPrice(items.getOrderPrice())
                .build();
    }

    public static OrderedItemsEntity mapOrderedItemsClientRequestToOrderEntity(
            OrderedItemsClientRequest items) {
        return OrderedItemsEntity.builder()
                .cartId(items.getCartId())
                .orderPrice(items.getOrderPrice())
                .build();
    }

}

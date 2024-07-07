package com.company.order.mapper.client;

import com.company.order.dto.client.order.OrderClientDetailedResponse;
import com.company.order.dto.client.order.OrderClientRequest;
import com.company.order.dto.client.order.OrderClientResponse;
import com.company.order.entity.OrderEntity;
import org.springframework.stereotype.Component;

import static com.company.order.mapper.client.OrderedItemsClientMapper.mapToOrderedItemsClientResponse;

@Component
public class OrderClientMapper {

    public static OrderClientResponse mapToOrderClientResponse(OrderEntity order) {
        return OrderClientResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .firstName(order.getFirstName())
                .lastName(order.getLastName())
                .email(order.getEmail())
                .build();
    }

    public static OrderClientDetailedResponse mapToOrderClientDetailedResponse(OrderEntity order) {
        return OrderClientDetailedResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .firstName(order.getFirstName())
                .lastName(order.getLastName())
                .email(order.getEmail())
                .orderedItemsClientResponse(
                        mapToOrderedItemsClientResponse(
                                order.getOrderedItems()
                        )
                )
                .build();
    }

    public static OrderEntity mapOrderClientRequestToOrderEntity(OrderClientRequest order) {
        return OrderEntity.builder()
                .orderCode(order.getOrderCode())
                .firstName(order.getFirstName())
                .lastName(order.getLastName())
                .email(order.getEmail())
                .version(order.getVersion())
                .build();
    }

}

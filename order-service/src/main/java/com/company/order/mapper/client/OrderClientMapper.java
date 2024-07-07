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
                .profileId(order.getProfileId())
                .orderCode(order.getOrderCode())
                .build();
    }

    public static OrderClientDetailedResponse mapToOrderClientDetailedResponse(OrderEntity order) {
        return OrderClientDetailedResponse.builder()
                .id(order.getId())
                .profileId(order.getProfileId())
                .orderCode(order.getOrderCode())
                .orderedItemsClientResponse(
                        mapToOrderedItemsClientResponse(
                                order.getOrderedItems()
                        )
                )
                .build();
    }

    public static OrderEntity mapOrderClientRequestToOrderEntity(OrderClientRequest order) {
        return OrderEntity.builder()
                .profileId(order.getProfileId())
                .orderCode(order.getOrderCode())
                .version(order.getVersion())
                .build();
    }

}

package com.company.order.mapper.client;

import com.company.order.dto.client.OrderClientRequest;
import com.company.order.dto.client.OrderClientResponse;
import com.company.order.entity.OrderEntity;
import org.springframework.stereotype.Component;



@Component
public class OrderClientMapper {

    public static OrderClientResponse mapToOrderClientResponse(OrderEntity order) {
        return OrderClientResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .cartId(order.getCartId())
                .orderPrice(order.getOrderPrice())
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

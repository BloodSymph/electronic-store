package com.company.order.mapper.admin;

import com.company.order.dto.admin.OrderAdminResponse;
import com.company.order.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderAdminMapper {

    public static OrderAdminResponse mapToOrderAdminResponse(OrderEntity order) {
        return OrderAdminResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .cartId(order.getCartId())
                .orderPrice(order.getOrderPrice())
                .created(order.getCreated())
                .updated(order.getUpdated())
                .version(order.getVersion())
                .build();
    }

}

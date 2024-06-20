package com.company.order.mapper.client;

import com.company.order.dto.client.CartClientResponse;
import com.company.order.entity.CartEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartClientMapper {

    public static CartClientResponse mapToCartClientResponse(CartEntity cart) {
        return CartClientResponse.builder()
                .id(cart.getId())
                .items(
                        cart.getItems()
                                .stream()
                                .map(ItemClientMapper::mapToCartClientResponse)
                                .collect(Collectors.toSet()
                                )
                )
                .build();
    }

}

package com.company.cart.mapper.client;

import com.company.cart.dto.client.CartClientResponse;
import com.company.cart.dto.client.ItemClientResponse;
import com.company.cart.entity.CartEntity;
import com.company.cart.entity.ItemEntity;
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
                                .map(ItemClientMapper::mapToItemClientResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }


}

package com.company.cart.mapper.client;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientDetailedResponse;
import com.company.cart.dto.client.cart.CartClientResponse;
import com.company.cart.entity.CartEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;



@Component
public class CartClientMapper {

    public static CartClientDetailedResponse mapToCartDetailedClientResponse(CartEntity cart) {
        return CartClientDetailedResponse.builder()
                .id(cart.getId())
                .profileId(cart.getProfileId())
                .items(
                        cart.getItems()
                                .stream()
                                .map(ItemClientMapper::mapToItemClientResponse)
                                .collect(Collectors.toList()))
                .build();
    }

    public static CartClientResponse mapToCartClientResponse(CartEntity cart) {
        return CartClientResponse.builder()
                .id(cart.getId())
                .profileId(cart.getProfileId())
                .build();
    }

    public static CartEntity mapToCartEntity(CartClientRequest cart) {
        return CartEntity.builder()
                .profileId(cart.getProfileId())
                .version(cart.getVersion())
                .build();
    }

}

package com.company.cart.mapper.client;

import com.company.cart.dto.client.cart.CartClientRequest;
import com.company.cart.dto.client.cart.CartClientResponse;
import com.company.cart.entity.CartEntity;
import com.company.cart.mapper.feign.ItemFeignClientMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartClientMapper {

    public static CartClientResponse mapToCartClientResponse(CartEntity cart) {
        return CartClientResponse.builder()
                .id(cart.getId())
                .profileId(cart.getProfileId())
                .items(
                        cart.getItems()
                                .stream()
                                .map(ItemFeignClientMapper::mapToItemFeignClientResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static CartEntity mapToCartEntity(CartClientRequest cart) {
        return CartEntity.builder()
                .profileId(cart.getProfileId())
                .version(cart.getVersion())
                .build();
    }

}
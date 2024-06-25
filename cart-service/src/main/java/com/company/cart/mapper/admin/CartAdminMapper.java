package com.company.cart.mapper.admin;

import com.company.cart.dto.admin.CartAdminResponse;
import com.company.cart.dto.admin.CartDetailedAdminResponse;
import com.company.cart.entity.CartEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartAdminMapper {

    public static CartAdminResponse mapToCartAdminResponse(CartEntity cart) {
        return CartAdminResponse.builder()
                .id(cart.getId())
                .profileId(cart.getProfileId())
                .created(cart.getCreated())
                .updated(cart.getUpdated())
                .version(cart.getVersion())
                .build();
    }

    public static CartDetailedAdminResponse mapToCartDetailedAdminResponse(CartEntity cart) {
        return CartDetailedAdminResponse.builder()
                .id(cart.getId())
                .profileId(cart.getProfileId())
                .created(cart.getCreated())
                .updated(cart.getUpdated())
                .items(
                        cart.getItems()
                                .stream()
                                .map(ItemAdminMapper::mapToItemAdminResponse)
                                .collect(Collectors.toSet())
                )
                .version(cart.getVersion())
                .build();
    }

}

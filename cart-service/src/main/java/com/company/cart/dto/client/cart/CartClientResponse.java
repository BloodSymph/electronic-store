package com.company.cart.dto.client.cart;

import com.company.cart.dto.client.item.ItemClientResponse;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartClientResponse {

    private Long id;

    private Long profileId;

    private Set<ItemClientResponse> items;

}

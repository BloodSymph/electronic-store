package com.company.cart.dto.client.cart;

import com.company.cart.dto.client.item.ItemClientResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CartClientDetailedResponse extends CartClientResponse {

    private List<ItemClientResponse> items;

}


package com.company.cart.dto.admin.cart;

import com.company.cart.dto.admin.item.ItemAdminResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CartDetailedAdminResponse extends CartAdminResponse {

    private List<ItemAdminResponse> items;

}

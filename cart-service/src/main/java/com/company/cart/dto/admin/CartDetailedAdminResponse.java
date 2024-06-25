package com.company.cart.dto.admin;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CartDetailedAdminResponse extends CartAdminResponse {

    private Set<ItemAdminResponse> items;

}

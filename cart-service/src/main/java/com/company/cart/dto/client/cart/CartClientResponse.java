package com.company.cart.dto.client.cart;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartClientResponse {

    private Long id;

    private Long profileId;

}

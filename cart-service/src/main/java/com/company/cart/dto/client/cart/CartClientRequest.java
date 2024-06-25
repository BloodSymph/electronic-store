package com.company.cart.dto.client.cart;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartClientRequest {

    @NotNull(message = "User profile id field shod not contains null value!")
    private Long profileId;

    @NotNull(message = "Cart version field shod not contains null value!")
    private Long version;

}

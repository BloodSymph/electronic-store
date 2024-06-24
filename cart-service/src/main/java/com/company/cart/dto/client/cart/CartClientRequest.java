package com.company.cart.dto.client.cart;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartClientRequest {

    @NotNull(message = "User profile id field shod not contains null value!")
    private Long profileId;

    @NotNull(message = "Cart version field shod not contains null value!")
    private Long version;

}

package com.company.order.dto.client;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartClientRequest {

    private Set<ItemClientResponse> items;

    @NotNull(message = "Cart version field shod not contains null value!")
    private Long version;

}

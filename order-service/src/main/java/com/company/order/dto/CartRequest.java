package com.company.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartRequest {

    private Set<ItemResponse> items;

    @NotNull(message = "Cart version field shod not contains null value!")
    private Long version;

}

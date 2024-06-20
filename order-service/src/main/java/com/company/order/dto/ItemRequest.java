package com.company.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemRequest {

    private Long productId;

    @NotNull(message = "Items version field shod not contains null value!")
    private Long version;

}

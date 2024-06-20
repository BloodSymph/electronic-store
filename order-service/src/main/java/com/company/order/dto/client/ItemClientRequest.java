package com.company.order.dto.client;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemClientRequest {

    private Long productId;

    @NotNull(message = "Items version field shod not contains null value!")
    private Long version;

}

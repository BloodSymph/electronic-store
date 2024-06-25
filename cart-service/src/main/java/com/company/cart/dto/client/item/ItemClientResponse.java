package com.company.cart.dto.client.item;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemClientResponse {

    private Long id;

    private Long productId;

    private Double price;

}

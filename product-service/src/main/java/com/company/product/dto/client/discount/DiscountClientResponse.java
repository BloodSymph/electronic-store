package com.company.product.dto.client.discount;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountClientResponse {

    private Long id;

    private Double discount;

}

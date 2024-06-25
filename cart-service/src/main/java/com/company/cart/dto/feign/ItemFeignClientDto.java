package com.company.cart.dto.feign;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemFeignClientDto {

    private Long productId;

    private Double price;

}

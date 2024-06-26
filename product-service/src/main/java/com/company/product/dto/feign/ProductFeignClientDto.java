package com.company.product.dto.feign;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductFeignClientDto {

    private Long productId;

    private Double price;

}

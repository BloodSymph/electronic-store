package com.company.product.dto.feign;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductFeignClientDto {

    private Long id;

    private String title;

    private String url;

    private Double price;

    private Long version;

}

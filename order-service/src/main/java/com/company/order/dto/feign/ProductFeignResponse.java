package com.company.order.dto.feign;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductFeignResponse {

    private Long id;

    private String title;

    private String url;

    private Double price;

    private String photo;

}
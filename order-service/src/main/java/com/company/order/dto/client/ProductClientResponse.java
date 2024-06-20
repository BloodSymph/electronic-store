package com.company.order.dto.client;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductClientResponse {

    private Long id;

    private String title;

    private String url;

    private Double price;

    private String photo;

}

package com.company.product.dto.client.product;

import com.company.product.dto.client.discount.DiscountClientResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductClientResponse {

    private Long id;

    private String title;

    private String url;

    private Double price;

    private String photo;

    private DiscountClientResponse discount;

}

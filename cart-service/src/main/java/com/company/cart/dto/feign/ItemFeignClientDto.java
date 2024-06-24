package com.company.cart.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemFeignClientDto {

    private Long id;

    private String title;

    private String url;

    private Double price;

    private String photo;

    private Long version;

}

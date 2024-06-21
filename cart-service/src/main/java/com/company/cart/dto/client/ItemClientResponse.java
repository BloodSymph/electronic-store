package com.company.cart.dto.client;

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
public class ItemClientResponse {

    private Long id;

    private String title;

    private String url;

    private Double price;

    private String photo;

}

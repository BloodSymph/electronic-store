package com.company.product.dto.client.category;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryClientResponse {

    private Long id;

    private String name;

    private String url;

}

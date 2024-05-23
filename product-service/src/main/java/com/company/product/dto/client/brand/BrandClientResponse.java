package com.company.product.dto.client.brand;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandClientResponse {

    private Long id;

    private String name;

    private String url;

}

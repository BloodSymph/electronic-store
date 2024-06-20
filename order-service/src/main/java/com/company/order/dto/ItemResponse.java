package com.company.order.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemResponse {

    private Long id;

    private Long productId;

}

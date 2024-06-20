package com.company.order.dto;

import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartResponse {

    private Long id;

    private Set<ItemResponse> items;

}

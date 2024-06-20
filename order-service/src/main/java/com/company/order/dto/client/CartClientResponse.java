package com.company.order.dto.client;

import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartClientResponse {

    private Long id;

    private Set<ItemClientResponse> items;

    private Long version;


}

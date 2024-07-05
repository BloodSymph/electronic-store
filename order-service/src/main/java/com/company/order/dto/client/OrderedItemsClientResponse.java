package com.company.order.dto.client;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderedItemsClientResponse {

    private Long id;

    private Long itemsId;

    private Double orderPrice;

}

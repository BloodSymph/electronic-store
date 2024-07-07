package com.company.order.dto.client.ordered;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderedItemsClientResponse {

    private Long id;

    private Long cartId;

    private Double orderPrice;

}

package com.company.order.dto.client.order;

import com.company.order.dto.client.ordered.OrderedItemsClientResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderClientDetailedResponse extends OrderClientResponse {

    private OrderedItemsClientResponse orderedItemsClientResponse;

}

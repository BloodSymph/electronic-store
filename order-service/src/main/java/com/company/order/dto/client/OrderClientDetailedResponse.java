package com.company.order.dto.client;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderClientDetailedResponse extends OrderClientResponse {

    private List<OrderedItemsClientResponse> orderedItems;

}

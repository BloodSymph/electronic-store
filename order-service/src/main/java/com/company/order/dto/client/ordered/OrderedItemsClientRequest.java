package com.company.order.dto.client.ordered;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderedItemsClientRequest {

    @NotNull(message = "Ordered items field shod not contains null value!")
    private Long version;

}

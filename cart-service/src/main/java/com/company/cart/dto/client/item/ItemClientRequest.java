package com.company.cart.dto.client.item;

import com.company.cart.dto.feign.ItemFeignClientDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ItemClientRequest extends ItemFeignClientDto {

    @NotNull(message = "Items count field shod not contains null value!")
    @Range(max = 300, message ="Items count field shod have maximum of {max} characters!")
    private Integer itemsCount;

    @NotNull(message = "Cart version field shod not contains null value!")
    private Long version;

}

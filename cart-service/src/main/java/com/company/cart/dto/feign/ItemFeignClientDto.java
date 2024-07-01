package com.company.cart.dto.feign;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemFeignClientDto {

    @NotNull(message = "Product id field shod not contains null value!")
    private Long productId;

    @Range(max = 99999, message ="Items count field shod have maximum of {max} characters!")
    private Double price;

}

package com.company.product.dto.admin.discount;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountAdminRequest {

    @Size(max = 5, message ="Discount field shod have maximum of {max} characters!")
    @NotNull(message = "Discount field shod not contains null value!")
    private Double discount;

    @NotNull(message = "Discount version field shod not contains null value!")
    private Long version;

}

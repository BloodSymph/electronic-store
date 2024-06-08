package com.company.product.dto.admin.discount;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountAdminRequest {

    @Size(max = 5, message ="Discount field shod have maximum of {max} characters!")
    @NotNull(message = "Discount field shod not contains null value!")
    private Double discount;

    @Length(max = 25, message = "Product url field shod have maximum of {max} characters!")
    private String productUrl;

    @NotNull(message = "Discount version field shod not contains null value!")
    private Long version;

}

package com.company.product.dto.admin.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BrandAdminExtendedRequest extends BrandAdminRequest {

    @NotNull(message = "Category url field shod not contains null value!")
    @NotBlank(message = "Category url field shod not be empty!")
    @Length(max = 25, message = "Category url field shod have maximum of {max} characters!")
    private String categoryUrl;

}

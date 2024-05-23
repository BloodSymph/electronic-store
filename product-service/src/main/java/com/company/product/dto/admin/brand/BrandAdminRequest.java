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
public class BrandAdminRequest {

    @NotNull(message = "Brand name field shod not contains null value!")
    @NotBlank(message = "Brand name field shod not be empty!")
    @Length(max = 120, message = "Brand name field shod have maximum of {max} characters!")
    private String name;

    @Length(max = 25, message = "Brand url field shod have maximum of {max} characters!")
    private String url;

    @NotNull(message = "Brand version field shod not contains null value!")
    private Long version;

}

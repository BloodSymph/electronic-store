package com.company.product.dto.admin.description;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DescriptionAdminRequest {

    @NotNull(message = "Description field shod not contains null value!")
    @NotBlank(message = "Description field shod not be empty!")
    @Length(max = 6000, message = "Description field shod have maximum of {max} characters!")
    private String description;

    @Length(max = 25, message = "Product url field shod have maximum of {max} characters!")
    private String productUrl;

    @NotNull(message = "Description version field shod not contains null value!")
    private Long version;

}

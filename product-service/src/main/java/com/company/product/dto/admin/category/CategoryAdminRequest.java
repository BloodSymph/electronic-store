package com.company.product.dto.admin.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryAdminRequest {

    @NotNull(message = "Category name field shod not contains null value!")
    @NotBlank(message = "Category name field shod not be empty!")
    @Length(max = 120, message = "Category name field shod have maximum of {max} characters!")
    private String name;

    @Length(max = 25, message = "Category url field shod have maximum of {max} characters!")
    private String url;

    @NotNull(message = "Category version field shod not contains null value!")
    private Long version;

}

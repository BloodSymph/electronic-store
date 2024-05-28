package com.company.product.dto.admin.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductAdminRequest {

    @NotNull(message = "Product title field shod not contains null value!")
    @NotBlank(message = "Product title field shod not be empty!")
    @Length(max = 120, message = "Product title field shod have maximum of {max} characters!")
    private String title;

    @Length(max = 25, message = "Product url field shod have maximum of {max} characters!")
    private String url;

    @Size(max = 999999, message ="Product price field shod have maximum of {max} characters!")
    @NotNull(message = "Product price field shod not contains null value!")
    private Double price;

    @NotNull(message = "Product photo path field shod not contains null value!")
    @NotBlank(message = "Product photo path field shod not be empty!")
    @Length(max = 25, message = "Product photo path field shod have maximum of {max} characters!")
    private String photo;

    @NotNull(message = "Product version field shod not contains null value!")
    private Long version;

}

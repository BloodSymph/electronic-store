package com.company.product.dto.admin.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductAdminRequest {

    @NotEmpty
    @NotBlank(message = "Product title field shod not be empty!")
    @Length(max = 120, message = "Product title field shod have maximum of {max} characters!")
    private String title;

    @Length(max = 25, message = "Product url field shod have maximum of {max} characters!")
    private String url;

    @Length(max = 25, message = "Category url field shod have maximum of {max} characters!")
    private String categoryUrl;

    @Length(max = 25, message = "Brand url field shod have maximum of {max} characters!")
    private String brandUrl;

    @Range(max = 99999, message ="Product price field shod have maximum of {max} characters!")
    @NotNull(message = "Product price field shod not contains null value!")
    private Double price;

    @NotNull(message = "Product version field shod not contains null value!")
    private Long version;

}

package com.company.cart.dto.feign;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemFeignClientDto {

    private Long id;

    @NotNull(message = "Item title field shod not contains null value!")
    @NotBlank(message = "Item title field shod not be empty!")
    @Length(max = 120, message = "Item title field shod have maximum of {max} characters!")
    private String title;

    @Length(max = 25, message = "Item url field shod have maximum of {max} characters!")
    private String url;

    @Range(max = 99999, message ="Item price field shod have maximum of {max} characters!")
    @NotNull(message = "Item price field shod not contains null value!")
    private Double price;

    @NotNull(message = "Product version field shod not contains null value!")
    private Long version;

}

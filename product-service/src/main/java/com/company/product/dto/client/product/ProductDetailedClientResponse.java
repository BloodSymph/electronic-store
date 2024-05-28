package com.company.product.dto.client.product;

import com.company.product.dto.client.brand.BrandClientResponse;
import com.company.product.dto.client.category.CategoryClientResponse;
import com.company.product.dto.client.description.DescriptionClientResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductDetailedClientResponse extends ProductClientResponse{

    private DescriptionClientResponse description;

    private CategoryClientResponse category;

    private BrandClientResponse brand;

}

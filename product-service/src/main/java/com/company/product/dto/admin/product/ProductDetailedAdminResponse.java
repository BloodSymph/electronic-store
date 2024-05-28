package com.company.product.dto.admin.product;

import com.company.product.dto.admin.brand.BrandAdminResponse;
import com.company.product.dto.admin.category.CategoryAdminResponse;
import com.company.product.dto.admin.description.DescriptionAdminResponse;
import com.company.product.dto.admin.discount.DiscountAdminResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductDetailedAdminResponse extends ProductAdminResponse {

    private DescriptionAdminResponse description;

    private DiscountAdminResponse discount;

    private CategoryAdminResponse category;

    private BrandAdminResponse brand;

}

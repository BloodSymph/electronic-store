package com.company.product.dto.admin.brand;

import com.company.product.dto.admin.category.CategoryAdminResponse;
import com.company.product.dto.admin.product.ProductAdminResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BrandDetailedAdminResponse extends BrandAdminResponse {

    private Set<CategoryAdminResponse> categories;

    private Set<ProductAdminResponse> products;

}

package com.company.product.dto.admin.category;

import com.company.product.dto.admin.brand.BrandAdminResponse;
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
public class CategoryDetailedAdminResponse extends CategoryAdminResponse {

    private Set<BrandAdminResponse> brands;

    private Set<ProductAdminResponse> products;

}

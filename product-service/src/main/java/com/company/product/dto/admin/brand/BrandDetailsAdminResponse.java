package com.company.product.dto.admin.brand;

import com.company.product.dto.admin.category.CategoryAdminResponse;
import lombok.*;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BrandDetailsAdminResponse extends BrandAdminResponse {

    private Set<CategoryAdminResponse> categories;

}

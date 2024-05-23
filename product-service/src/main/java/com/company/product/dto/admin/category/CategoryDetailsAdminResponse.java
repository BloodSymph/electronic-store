package com.company.product.dto.admin.category;

import com.company.product.dto.admin.brand.BrandAdminResponse;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CategoryDetailsAdminResponse extends CategoryAdminResponse{

    private Set<BrandAdminResponse> brands;

}

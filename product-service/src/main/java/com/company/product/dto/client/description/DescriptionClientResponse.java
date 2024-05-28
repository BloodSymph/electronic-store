package com.company.product.dto.client.description;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DescriptionClientResponse {

    private Long id;

    private String description;

}

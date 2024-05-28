package com.company.product.mapper.client;

import com.company.product.dto.client.description.DescriptionClientResponse;
import com.company.product.entity.DescriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class DescriptionClientMapper {

    public static DescriptionClientResponse mapToDescriptionClientResponse(
            DescriptionEntity description) {
        return DescriptionClientResponse.builder()
                .id(description.getId())
                .description(description.getDescription())
                .build();
    }

}

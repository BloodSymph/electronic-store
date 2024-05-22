package com.company.product.mapper.client;

import com.company.product.dto.client.category.CategoryClientResponse;
import com.company.product.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryClientMapper {

    public static CategoryClientResponse mapToCategoryClientResponse(CategoryEntity category) {
        return CategoryClientResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .url(category.getUrl())
                .build();
    }

}

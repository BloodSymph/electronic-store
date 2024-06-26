package com.company.product.mapper.admin;

import com.company.product.dto.admin.category.CategoryAdminRequest;
import com.company.product.dto.admin.category.CategoryAdminResponse;
import com.company.product.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryAdminMapper {

    public static CategoryAdminResponse mapToCategoryAdminResponse(
            CategoryEntity category) {

        return CategoryAdminResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .url(category.getUrl())
                .created(category.getCreated())
                .updated(category.getUpdated())
                .version(category.getVersion())
                .build();
    }

    public static CategoryEntity mapRequestToCategoryEntity(
            CategoryAdminRequest categoryAdminRequest) {
        return CategoryEntity.builder()
                .name(categoryAdminRequest.getName())
                .url(categoryAdminRequest.getUrl())
                .version(categoryAdminRequest.getVersion())
                .build();
    }


}

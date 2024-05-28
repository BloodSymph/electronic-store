package com.company.product.mapper.admin;

import com.company.product.dto.admin.description.DescriptionAdminRequest;
import com.company.product.dto.admin.description.DescriptionAdminResponse;
import com.company.product.entity.DescriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class DescriptionAdminMapper {

    public static DescriptionAdminResponse mapToDescriptionAdminResponse(
            DescriptionEntity description) {

        return DescriptionAdminResponse.builder()
                .id(description.getId())
                .description(description.getDescription())
                .created(description.getCreated())
                .updated(description.getUpdated())
                .version(description.getVersion())
                .build();

    }

    public static DescriptionEntity mapRequestToDescriptionEntity(
            DescriptionAdminRequest descriptionAdminRequest) {

        return DescriptionEntity.builder()
                .description(descriptionAdminRequest.getDescription())
                .version(descriptionAdminRequest.getVersion())
                .build();

    }

}

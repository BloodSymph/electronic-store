package com.company.product.service.admin;

import com.company.product.dto.admin.description.DescriptionAdminRequest;
import com.company.product.dto.admin.description.DescriptionAdminResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface DescriptionAdminService {

    DescriptionAdminResponse createProductDescription(
            DescriptionAdminRequest descriptionAdminRequest
    );

    DescriptionAdminResponse updateProductDescription(
            DescriptionAdminRequest descriptionAdminRequest, String productUrl
    );

    void deleteCurrentDescription(
            String productUrl, Long descriptionVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}

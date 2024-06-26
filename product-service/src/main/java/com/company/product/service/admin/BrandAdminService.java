package com.company.product.service.admin;

import com.company.product.dto.admin.brand.BrandAdminRequest;
import com.company.product.dto.admin.brand.BrandAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface BrandAdminService {

    Page<BrandAdminResponse> getAllBrands(Pageable pageable);

    Page<BrandAdminResponse> searchBrands(
            Pageable pageable, String brandName
    );

    BrandAdminResponse createNewBrand(BrandAdminRequest brandAdminRequest);

    BrandAdminResponse updateCurrentBrand(
            BrandAdminRequest brandAdminRequest, String brandUrl
    );

    void deleteCurrentBrand(
            String brandUrl, Long brandVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}

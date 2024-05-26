package com.company.product.service.admin;

import com.company.product.dto.admin.brand.BrandAdminRequest;
import com.company.product.dto.admin.brand.BrandAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandAdminService {

    Page<BrandAdminResponse> getAllBrands(Pageable pageable);

    List<BrandAdminResponse> searchBrands(String brandName);

    BrandAdminResponse createNewBrand(BrandAdminRequest brandAdminRequest);

    BrandAdminResponse updateCurrentBrand(
            BrandAdminRequest brandAdminRequest,
            String brandUrl
    );

    void deleteCurrentBrand(String brandUrl, Long brandVersion);

}

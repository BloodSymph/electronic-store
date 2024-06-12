package com.company.product.service.admin;

import com.company.product.dto.admin.discount.DiscountAdminRequest;
import com.company.product.dto.admin.discount.DiscountAdminResponse;
import org.springframework.stereotype.Service;

@Service
public interface DiscountAdminService {

    DiscountAdminResponse createDiscountForProduct(
            DiscountAdminRequest discountAdminRequest
    );

    DiscountAdminResponse updateDiscountForProduct(
            DiscountAdminRequest discountAdminRequest, String productUrl
    );

    void deleteCurrentDiscount(
            String productUrl, Long discountVersion
    );

}

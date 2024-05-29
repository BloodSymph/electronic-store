package com.company.product.service.admin;

import com.company.product.dto.admin.product.ProductAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductAdminService {

    Page<ProductAdminResponse> getAllProducts(Pageable pageable);



}

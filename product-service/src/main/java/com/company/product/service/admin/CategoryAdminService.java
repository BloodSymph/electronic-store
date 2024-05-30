package com.company.product.service.admin;

import com.company.product.dto.admin.category.CategoryAdminRequest;
import com.company.product.dto.admin.category.CategoryAdminResponse;
import com.company.product.dto.admin.category.CategoryDetailedAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryAdminService {

    Page<CategoryAdminResponse> getAllCategories(Pageable pageable);

    Page<CategoryAdminResponse> searchCategories(Pageable pageable, String categoryName);

    CategoryDetailedAdminResponse getDetailsAboutCategory(String categoryUrl);

    CategoryAdminResponse createNewCategory(CategoryAdminRequest categoryAdminRequest);

    CategoryAdminResponse updateCurrentCategory(
            CategoryAdminRequest categoryAdminRequest,
            String categoryUrl
    );

    void deleteCurrentCategory(String categoryUrl, Long categoryVersion);

    @Scheduled(fixedRate = 6000)
    void evictAllCacheWithTime();

}

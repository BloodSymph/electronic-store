package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.category.CategoryAdminRequest;
import com.company.product.dto.admin.category.CategoryAdminResponse;
import com.company.product.entity.CategoryEntity;
import com.company.product.exception.exceptions.CategoryNotFoundException;
import com.company.product.exception.exceptions.CategoryVersionNotValidException;
import com.company.product.mapper.admin.CategoryAdminMapper;
import com.company.product.repository.CategoryRepository;
import com.company.product.service.admin.CategoryAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.product.mapper.admin.CategoryAdminMapper.mapRequestToCategoryEntity;
import static com.company.product.mapper.admin.CategoryAdminMapper.mapToCategoryAdminResponse;
import static com.company.product.util.URLGenerator.toUrlAddress;

@Service
@RequiredArgsConstructor
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryAdminResponse> getAllCategories(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .map(CategoryAdminMapper::mapToCategoryAdminResponse);
    }

    @Override
    public List<CategoryAdminResponse> searchCategories(String categoryName) {
        List<CategoryEntity> categoryEntities = categoryRepository.searchByNameIgnoreCase(categoryName);
        return categoryEntities.stream()
                .map(CategoryAdminMapper::mapToCategoryAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryAdminResponse createNewCategory(CategoryAdminRequest categoryAdminRequest) {
        CategoryEntity category = mapRequestToCategoryEntity(categoryAdminRequest);
        category.setUrl(toUrlAddress(categoryAdminRequest.getName()));
        categoryRepository.save(category);
        return mapToCategoryAdminResponse(category);
    }

    @Override
    @Transactional
    public CategoryAdminResponse updateCurrentCategory(CategoryAdminRequest categoryAdminRequest,
                                                       String categoryUrl) {
        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(categoryUrl)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not find category by current url: " + categoryUrl + " !"
                        )
                );

        if (!category.getVersion().equals(categoryAdminRequest.getVersion())) {
            throw new CategoryVersionNotValidException(
                    "Category Entity version: " + categoryAdminRequest.getVersion() + " not valid!"
            );
        }

        category = mapRequestToCategoryEntity(categoryAdminRequest);
        category.setUrl(toUrlAddress(categoryAdminRequest.getName()));
        categoryRepository.save(category);

        return mapToCategoryAdminResponse(category);
    }

    @Override
    @Transactional
    public void deleteCurrentCategory(String categoryUrl, Long categoryVersion) {
        if (!categoryRepository.existsByUrlIgnoreCase(categoryUrl)) {
            throw new CategoryNotFoundException(
                    "Can not find category by current url: " + categoryUrl + " !"
            );
        }
        if (!categoryRepository.existsByVersion(categoryVersion)) {
            throw new CategoryVersionNotValidException(
                    "Category Entity version: " + categoryVersion + " not valid!"
            );
        }
        categoryRepository.deleteByUrlIgnoreCase(categoryUrl);
    }

}

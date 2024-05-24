package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.brand.BrandAdminExtendedRequest;
import com.company.product.dto.admin.brand.BrandAdminRequest;
import com.company.product.dto.admin.brand.BrandAdminResponse;
import com.company.product.entity.BrandEntity;
import com.company.product.entity.CategoryEntity;
import com.company.product.exception.exceptions.BrandNotFoundException;
import com.company.product.exception.exceptions.BrandVersionNotValidException;
import com.company.product.exception.exceptions.CategoryNotFoundException;
import com.company.product.mapper.admin.BrandAdminMapper;
import com.company.product.repository.BrandRepository;
import com.company.product.repository.CategoryRepository;
import com.company.product.service.admin.BrandAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.product.mapper.admin.BrandAdminMapper.mapToBrandAdminResponse;
import static com.company.product.mapper.admin.BrandAdminMapper.mapToBrandEntityAdminRequest;
import static com.company.product.util.URLGenerator.toUrlAddress;

@Service
@RequiredArgsConstructor
public class BrandAdminServiceImpl implements BrandAdminService {

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Page<BrandAdminResponse> getAllBrands(Pageable pageable) {
        return brandRepository
                .findAll(pageable)
                .map(BrandAdminMapper::mapToBrandAdminResponse);
    }

    @Override
    public List<BrandAdminResponse> searchBrands(String brandName) {
        List<BrandEntity> brandEntities = brandRepository.searchByNameIgnoreCase(brandName);
        return brandEntities.stream()
                .map(BrandAdminMapper::mapToBrandAdminResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BrandAdminResponse createNewBrand(
            BrandAdminExtendedRequest brandAdminExtendedRequest) {

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(brandAdminExtendedRequest.getCategoryUrl())
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not find category by current url: " +
                                        brandAdminExtendedRequest.getCategoryUrl() + " !"
                        )
                );

        BrandEntity brand = new BrandEntity();

        brand.setName(brandAdminExtendedRequest.getName());
        brand.setUrl(toUrlAddress(brandAdminExtendedRequest.getName()));
        brand.setVersion(brandAdminExtendedRequest.getVersion());
        brand.getCategories().add(category);

        BrandEntity newBrand = brandRepository.save(brand);

        return mapToBrandAdminResponse(newBrand);
    }

    @Transactional
    @Override
    public BrandAdminResponse updateCurrentBrand(
            BrandAdminRequest brandAdminRequest, String brandUrl) {

        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(brandUrl)
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not find brand by current url: " + brandUrl + " !"
                        )
                );

        if (!brand.getVersion().equals(brandAdminRequest.getVersion())) {
            throw new BrandVersionNotValidException(
                    "Brand Entity version: " + brandAdminRequest.getVersion() + " not valid!"
            );
        }

        brand.setName(brandAdminRequest.getName());
        brand.setUrl(toUrlAddress(brandAdminRequest.getUrl()));
        brand.setVersion(brandAdminRequest.getVersion());
        BrandEntity updatedBrand = brandRepository.save(brand);

        return mapToBrandAdminResponse(updatedBrand);
    }

    @Transactional
    @Override
    public void deleteCurrentBrand(String brandUrl, Long brandVersion) {
        if (!brandRepository.existsByUrlIgnoreCase(brandUrl)) {
            throw new BrandNotFoundException(
                    "Can not find brand by current url: " + brandUrl + " !"
            );
        }
        if (!brandRepository.existsByVersion(brandVersion)) {
            throw new BrandVersionNotValidException(
                    "Brand Entity version: " + brandVersion + " not valid!"
            );
        }
        brandRepository.deleteByUrlIgnoreCase(brandUrl);
    }

}

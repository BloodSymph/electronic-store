package com.company.product.service.admin.implementation;

import com.company.product.dto.admin.product.ProductAdminRequest;
import com.company.product.dto.admin.product.ProductAdminResponse;
import com.company.product.dto.admin.product.ProductDetailedAdminResponse;
import com.company.product.entity.ProductEntity;
import com.company.product.exception.exceptions.file.SearchingFileDirectoryException;
import com.company.product.mapper.admin.ProductAdminMapper;
import com.company.product.repository.ProductRepository;
import com.company.product.service.admin.ProductAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.company.product.util.CacheEvictUtility.evictAllCaches;
import static com.company.product.util.FileUtility.encodeFile;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"products"})
public class ProductAdminServiceImp implements ProductAdminService {

    private final ProductRepository productRepository;

    @Value("${web.resources.static-locations}")
    private static String filePath;

    @Override
    public Page<ProductAdminResponse> getAllProducts(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);
        products.forEach(product -> {
            try {
                product.setPhoto(encodeFile(product.getPhoto()));
            } catch (IOException e) {
                throw new SearchingFileDirectoryException(
                        "Can not find file by source directory: " + product.getPhoto() + " !"
                );
            }
        });
        return products.map(ProductAdminMapper::mapToProductAdminResponse);
    }

    @Override
    public Page<ProductAdminResponse> searchProducts(
            Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public ProductDetailedAdminResponse getDetailsAboutProduct(String productUrl) {
        return null;
    }

    @Override
    public ProductAdminResponse createNewProduct(
            ProductAdminRequest productAdminRequest) {
        return null;
    }

    @Override
    public ProductAdminResponse updateCurrentProduct(
            ProductAdminRequest productAdminRequest, String productUrl) {
        return null;
    }

    @Override
    public void deleteCurrentProduct(
            String productUrl, Long productVersion) {

    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}

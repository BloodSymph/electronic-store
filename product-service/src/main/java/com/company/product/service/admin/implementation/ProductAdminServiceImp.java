package com.company.product.service.admin.implementation;

import com.company.product.config.PropertiesConfig;
import com.company.product.dto.admin.file.FileAdminRequest;
import com.company.product.dto.admin.product.ProductAdminRequest;
import com.company.product.dto.admin.product.ProductAdminResponse;
import com.company.product.dto.admin.product.ProductDetailedAdminResponse;
import com.company.product.entity.BrandEntity;
import com.company.product.entity.CategoryEntity;
import com.company.product.entity.ProductEntity;
import com.company.product.exception.exceptions.brand.BrandNotFoundException;
import com.company.product.exception.exceptions.category.CategoryNotFoundException;
import com.company.product.exception.exceptions.product.ProductNotFoundException;
import com.company.product.exception.exceptions.product.ProductVersionNotValidException;
import com.company.product.mapper.admin.ProductAdminMapper;
import com.company.product.repository.BrandRepository;
import com.company.product.repository.CategoryRepository;
import com.company.product.repository.ProductRepository;
import com.company.product.service.admin.ProductAdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;


import static com.company.product.mapper.admin.ProductAdminMapper.*;
import static com.company.product.util.CacheEvictUtility.evictAllCaches;
import static com.company.product.util.FileUtility.*;
import static com.company.product.util.RandomFileNameGenerator.randomFileNameGenerator;
import static com.company.product.util.URLGeneratorUtility.toUrlAddress;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"products"})
public class ProductAdminServiceImp implements ProductAdminService {

    private final PropertiesConfig propertiesConfig;

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ProductAdminResponse> getAllProducts(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);
        products.forEach(product -> {
            try {
                if (product.getPhoto().equals(propertiesConfig.getFilePath())) {
                    product.setPhoto(encodeFile(product.getPhoto()));
                } else {
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return products.map(ProductAdminMapper::mapToProductAdminResponse);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ProductAdminResponse> searchProducts(
            Pageable pageable, String searchText) {
        Page<ProductEntity> products = productRepository.searchProducts(pageable, searchText);
        products.forEach(product -> {
            try {
                if (product.getPhoto().equals(propertiesConfig.getFilePath())) {
                    product.setPhoto(encodeFile(product.getPhoto()));
                } else {
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return products.map(ProductAdminMapper::mapToProductAdminResponse);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public ProductDetailedAdminResponse getDetailsAboutProduct(
            String productUrl) throws IOException {
        ProductEntity product = productRepository
                .getDetailsAboutProduct(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not find product by current url: " + productUrl + " !"
                        )
                );

        if (product.getPhoto().equals(propertiesConfig.getFilePath())) {
            product.setPhoto(encodeFile(product.getPhoto()));
        }

        return mapToProductDetailedAdminResponse(product);
    }

    @Override
    @Transactional
    @CachePut(unless = "#result == null ")
    public ProductAdminResponse createNewProduct(
            ProductAdminRequest productAdminRequest){

        CategoryEntity category = categoryRepository
                .findByUrlIgnoreCase(productAdminRequest.getCategoryUrl())
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Can not find category by current url: " + productAdminRequest.getCategoryUrl() + " !"
                        )
                );

        BrandEntity brand = brandRepository
                .findByUrlIgnoreCase(productAdminRequest.getBrandUrl())
                .orElseThrow(
                        () -> new BrandNotFoundException(
                                "Can not find brand by current url: " + productAdminRequest.getBrandUrl() + " !"
                        )
                );



        ProductEntity product = mapRequestToProductEntity(productAdminRequest);
        product.setUrl(toUrlAddress(productAdminRequest.getTitle()));
        product.setPhoto(propertiesConfig.getFilePath());
        product.setCategory(category);
        product.setBrand(brand);


        productRepository.save(product);

        return mapToProductAdminResponse(product);
    }

    @Override
    @Transactional
    @Async("fileExecutor")
    public CompletableFuture<ProductAdminResponse> createPhotoForProduct(
            FileAdminRequest fileAdminRequest, String productUrl) throws IOException {
        ProductEntity product = productRepository
                .getDetailsAboutProduct(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not find product by current url: " + productUrl + " !"
                        )
                );

       String decodedFile = decodeFile(
               randomFileNameGenerator(), fileAdminRequest.getEncodedFile()
       );

       product.setPhoto(product.getPhoto().concat(decodedFile));

       productRepository.save(product);

       writeFile(
               randomFileNameGenerator(),
               propertiesConfig.getFilePath(),
               fileAdminRequest.getEncodedFile()
       );


       return CompletableFuture.completedFuture(mapToProductAdminResponse(product));
    }

    @Override
    @Async("fileExecutor")
    @Transactional
    public CompletableFuture<ProductAdminResponse> deletePhotoForProduct(
            String productUrl) throws IOException {
        ProductEntity product = productRepository
                .getDetailsAboutProduct(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not find product by current url: " + productUrl + " !"
                        )
                );

        deleteFile(product.getPhoto());
        product.setPhoto("");
        productRepository.save(product);
        return CompletableFuture.completedFuture(mapToProductAdminResponse(product));
    }

    @Override
    @Transactional
    @CachePut(unless = "#result == null ")
    public ProductAdminResponse updateCurrentProduct(
            ProductAdminRequest productAdminRequest, String productUrl) {

        ProductEntity product = productRepository
                .findByUrlIgnoreCase(productUrl)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Can not find product by current url: " + productUrl + " !"
                        )
                );

        if (!product.getVersion().equals(productAdminRequest.getVersion())) {
            throw new ProductVersionNotValidException(
                    "Product Entity version: " + productAdminRequest.getVersion() + " not valid!"
            );
        }

        product.setTitle(productAdminRequest.getTitle());
        product.setUrl(toUrlAddress(product.getTitle()));
        product.setPrice(productAdminRequest.getPrice());

        productRepository.save(product);

        return mapToProductAdminResponse(product);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deleteCurrentProduct(
            String productUrl, Long productVersion) {
        if (!productRepository.existsByUrlIgnoreCase(productUrl)) {
            throw new ProductNotFoundException(
                    "Can not find product by current url: " + productUrl + " !"
            );
        }
        if (!productRepository.existsByVersion(productVersion)) {
            throw new ProductVersionNotValidException(
                    "Product Entity version: " + productVersion + " not valid!"
            );
        }
        productRepository.deleteByUrlIgnoreCase(productUrl);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }
}

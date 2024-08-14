package com.company.product.repository;

import com.company.product.entity.ProductEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.NonNullApi;


import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT products FROM Product products " +
            "INNER JOIN products.category category " +
            "ON category.url LIKE LOWER(:categoryUrl) ")
    Page<ProductEntity> findByCategory(
            Pageable pageable,
            @Param(value = "categoryUrl") String categoryUrl
    );


    @Query("SELECT products FROM Product products " +
            "INNER JOIN products.brand brand " +
            "ON brand.url LIKE LOWER(:brandUrl) ")
    Page<ProductEntity> findByBrand(
            Pageable pageable,
            @Param(value = "brandUrl") String brandUrl
    );

    @Query("SELECT products FROM Product products " +
        "WHERE LOWER(products.title) LIKE LOWER(CONCAT('%', :searchText ,'%') ) " +
        "OR LOWER(products.category.name) LIKE LOWER(CONCAT('%', :searchText ,'%') ) " +
        "OR LOWER(products.brand.name) LIKE LOWER(CONCAT('%', :searchText ,'%') ) ")
    Page<ProductEntity> searchProducts(
            Pageable pageable,
            @Param(value = "searchText") String searchText
    );

    @EntityGraph(value = "product-detailed-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT product FROM Product product WHERE product.url LIKE LOWER(:productUrl) ")
    Optional<ProductEntity> getDetailsAboutProduct(
            @Param("productUrl") String productUrl
    );

    Optional<ProductEntity> findByUrlIgnoreCase(String productUrl);

    @Query("SELECT product.title FROM Product product WHERE product.url LIKE LOWER(:productUrl) ")
    String getProductTitleByUrlIgnoreCase(
            @Param("productUrl") String productUrl
    );

    @NonNull
    Optional<ProductEntity> findById(@NonNull Long productId);

    void deleteByUrlIgnoreCase(String productUrl);

    Boolean existsByVersion(Long version);

    Boolean existsByUrlIgnoreCase(String productUrl);
}

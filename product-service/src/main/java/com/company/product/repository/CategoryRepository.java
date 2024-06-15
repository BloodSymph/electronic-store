package com.company.product.repository;

import com.company.product.entity.BrandEntity;
import com.company.product.entity.CategoryEntity;
import jakarta.ws.rs.Path;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT category FROM Category category " +
            "WHERE LOWER(category.name) " +
            "LIKE LOWER(CONCAT('%', :categoryName, '%') ) ")
    Page<CategoryEntity> searchByNameIgnoreCase(
            Pageable pageable,
            @Param(value = "categoryName") String categoryName
    );

    @Query("SELECT category FROM Category category " +
            "INNER JOIN category.brands brands " +
            "ON brands.url LIKE LOWER(:brandUrl) ")
    List<CategoryEntity> findCategoriesByBrand(
            @Param(value = "brandUrl") String brandUrl
    );

    Optional<CategoryEntity> findByUrlIgnoreCase(String categoryUrl);

    void deleteByUrlIgnoreCase(String categoryUrl);

    Boolean existsByUrlIgnoreCase(String categoryUrl);

    Boolean existsByVersion(Long categoryVersion);

}

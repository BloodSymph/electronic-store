package com.company.product.repository;

import com.company.product.entity.BrandEntity;
import com.company.product.entity.CategoryEntity;
import jakarta.ws.rs.Path;
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
    List<CategoryEntity> searchByNameIgnoreCase(
            @Param(value = "categoryName") String categoryName
    );

    Optional<CategoryEntity> findByUrlIgnoreCase(String categoryUrl);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            value = "category-details-entity-graph"
    )
    @Query("SELECT category FROM Category category " +
            "WHERE category.url LIKE LOWER(:categoryUrl) ")
    Optional<CategoryEntity> getCategoryDetailsByUrlIgnoreCase(
            @Param(value = "categoryUrl") String categoryUrl
    );

    void deleteByUrlIgnoreCase(String categoryUrl);

    Boolean existsByUrlIgnoreCase(String categoryUrl);

    Boolean existsByVersion(Long categoryVersion);

}

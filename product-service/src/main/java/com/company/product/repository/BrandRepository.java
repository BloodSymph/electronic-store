package com.company.product.repository;

import com.company.product.entity.BrandEntity;
import com.company.product.entity.CategoryEntity;
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
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @Query("SELECT brand FROM Brand brand " +
            "WHERE LOWER(brand.name) " +
            "LIKE LOWER(CONCAT('%', :brandName, '%') ) ")
    Page<BrandEntity> searchByNameIgnoreCase(
            Pageable pageable,
            @Param(value = "brandName") String brandName
    );

    @Query("SELECT brand FROM Brand brand " +
            "INNER JOIN brand.categories categories " +
            "ON categories.url LIKE LOWER(:categoryUrl) ")
    List<BrandEntity> getByCategories(
            @Param(value = "categoryUrl") String categoryUrl
    );

    @EntityGraph(value = "brand-detailed-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT brand FROM Brand brand WHERE brand.url LIKE LOWER(:brandUrl) ")
    Optional<BrandEntity> getDetailsAboutBrand(
            @Param(value = "brandUrl") String brandUrl
    );

    Optional<BrandEntity> findByUrlIgnoreCase(String brandUrl);

    void deleteByUrlIgnoreCase(String brandUrl);

    Boolean existsByUrlIgnoreCase(String brandUrl);

    Boolean existsByVersion(Long brandVersion);

}

package com.company.product.repository;

import com.company.product.entity.CategoryEntity;
import jakarta.ws.rs.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT category FROM Category category WHERE category.name LIKE LOWER(:categoryName) ")
    List<CategoryEntity> searchByName(@Param("categoryName") String categoryName);

    Optional<CategoryEntity> findByUrlIgnoreCase(String categoryUrl);

    void deleteByUrlIgnoreCase(String categoryUrl);

    Boolean existsByUrlIgnoreCase(String categoryUrl);

}

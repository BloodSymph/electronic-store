package com.company.product.repository;

import com.company.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(value = "product-detailed-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT product FROM Product product WHERE product.url LIKE LOWER(:productUrl) ")
    Optional<ProductEntity> getDetailsAboutProduct(
            @Param("productUrl") String productUrl
    );


}

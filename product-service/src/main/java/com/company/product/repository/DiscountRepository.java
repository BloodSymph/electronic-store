package com.company.product.repository;

import com.company.product.entity.DescriptionEntity;
import com.company.product.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {


    @Query("SELECT discount FROM Discount discount " +
            "WHERE discount.product.url LIKE (LOWER(:productUrl) )")
    Optional<DiscountEntity> findByProduct_Url(
            @Param(value = "productUrl") String productUrl
    );

    @Query("SELECT discount FROM Discount discount " +
            "WHERE discount.product.url LIKE (LOWER(:productUrl) )")
    Boolean existsByProduct_Url(
            @Param(value = "productUrl") String productUrl
    );

    Boolean existsByVersion(Long version);

    @Modifying
    @Query("DELETE FROM Discount discount " +
            "WHERE discount.product.url LIKE LOWER(:productUrl) ")
    void deleteByProduct_Url(
            @Param(value = "productUrl") String productUrl
    );


}

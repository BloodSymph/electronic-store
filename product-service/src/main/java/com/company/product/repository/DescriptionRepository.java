package com.company.product.repository;

import com.company.product.entity.DescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Long> {

    @Query("SELECT description FROM Description description " +
            "WHERE description.product.url LIKE (LOWER(:productUrl) )")
    Optional<DescriptionEntity> findByProduct_Url(
           @Param(value = "productUrl") String productUrl
    );

    @Query("SELECT description FROM Description description " +
            "WHERE description.product.url LIKE (LOWER(:productUrl) )")
    Boolean existsByProduct_Url(
            @Param(value = "productUrl") String productUrl
    );

    Boolean existsByVersion(Long version);

    @Modifying
    @Query("DELETE FROM Description description " +
            "WHERE description.product.url LIKE LOWER(:productUrl) ")
    void deleteByProduct_Url(
            @Param(value = "productUrl") String productUrl
    );

}

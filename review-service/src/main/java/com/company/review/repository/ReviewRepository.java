package com.company.review.repository;

import com.company.review.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT avg(reviews.rate) FROM Review reviews " +
            "WHERE reviews.productTitle LIKE LOWER(:productTitle) "
    )
    Double getSummaryRatingOfProduct(
           @Param(value = "productTitle") String productTitle
    );

    @Query("SELECT reviews FROM Review reviews WHERE reviews.productTitle LIKE LOWER(:productTitle) ")
    Page<ReviewEntity> findByProductTitleIgnoreCase(
            Pageable pageable,
            @Param(value = "productTitle") String productTitle
    );

    @Query("SELECT reviews FROM Review reviews " +
            "WHERE LOWER(reviews.productTitle) " +
            "LIKE CONCAT('%', :productTitle ,'%') "
    )
    Page<ReviewEntity> searchByProductTitleIgnoreCase(
            Pageable pageable,
            @Param(value = "productTitle") String productTitle
    );

    void deleteByProfileId(Long profileId);

    Boolean existsByProfileId(Long profileId);

    Boolean existsByVersion(Long reviewVersion);

}

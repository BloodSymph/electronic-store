package com.company.review.repository;

import com.company.review.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    Page<ReviewEntity> findByProductId(Pageable pageable, Long productId);

    @Query(
            "SELECT reviews FROM Review reviews " +
            "WHERE reviews.profileId = :searchParameter " +
            "AND reviews.productId = :searchParameter"
    )
    Page<ReviewEntity> searchByProfileIdAndProductId(
            @Param(value = "searchParameter") Long searchParameter
    );

    void deleteByProfileId(Long profileId);

    Boolean existsByProfileId(Long profileId);

    Boolean existsByVersion(Long reviewVersion);

}

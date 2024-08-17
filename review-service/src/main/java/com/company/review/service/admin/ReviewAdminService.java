package com.company.review.service.admin;

import com.company.review.dto.admin.ReviewAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface ReviewAdminService {

    Page<ReviewAdminResponse> getAllReviews(Pageable pageable);

    Page<ReviewAdminResponse> searchReviews(
            Pageable pageable, String productTitle
    );

    void deleteReviewByProfileId(
            Long profileId, Long reviewVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}

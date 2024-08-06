package com.company.review.service.client;

import com.company.review.dto.client.ReviewClientRequest;
import com.company.review.dto.client.ReviewClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface ReviewClientService {

    Page<ReviewClientResponse> getAllReviews(Pageable pageable);

    ReviewClientResponse addReview(
            ReviewClientRequest reviewClientRequest, String productUrl
    );

    void deleteReview(
            Long profileId, Long reviewVersion
    );

    @Scheduled(fixedRate = 120)
    void evictAllCacheWithTime();

}

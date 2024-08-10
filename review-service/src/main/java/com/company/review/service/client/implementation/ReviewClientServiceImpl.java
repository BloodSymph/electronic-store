package com.company.review.service.client.implementation;

import com.company.review.dto.client.ReviewClientRequest;
import com.company.review.dto.client.ReviewClientResponse;
import com.company.review.entity.ReviewEntity;
import com.company.review.exception.exceptions.ReviewNotFoundException;
import com.company.review.exception.exceptions.ReviewVersionNotValidException;
import com.company.review.feign.ProductFeignClient;
import com.company.review.mapper.client.ReviewClientMapper;
import com.company.review.repository.ReviewRepository;
import com.company.review.service.client.ReviewClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.review.mapper.client.ReviewClientMapper.mapReviewClientRequestToEntity;
import static com.company.review.mapper.client.ReviewClientMapper.mapToReviewClientResponse;
import static com.company.review.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"reviews_client"})
public class ReviewClientServiceImpl implements ReviewClientService {

    private final ReviewRepository reviewRepository;

    private final ProductFeignClient productFeignClient;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ReviewClientResponse> getAllReviews(Pageable pageable, Long productId) {
        return reviewRepository
                .findByProductId(pageable, productId)
                .map(ReviewClientMapper::mapToReviewClientResponse);
    }

    @Override
    @Transactional
    public ReviewClientResponse addReview(
            ReviewClientRequest reviewClientRequest, String productUrl) {
        ReviewEntity review = mapReviewClientRequestToEntity(reviewClientRequest);
        review.setProductId(productFeignClient.getProductIdForReview(productUrl));
        return mapToReviewClientResponse(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long profileId, Long reviewVersion) {
        if (!reviewRepository.existsByProfileId(profileId)) {
            throw new ReviewNotFoundException(
                    "Can not find review by current profile id: " + profileId + " !"
            );
        }
        if (!reviewRepository.existsByVersion(reviewVersion)) {
            throw new ReviewVersionNotValidException(
                    "Review Entity version: " + reviewVersion + " not valid!"
            );
        }
        reviewRepository.deleteByProfileId(profileId);
    }

    @Override
    public void evictAllCacheWithTime() {
        evictAllCaches();
    }

}

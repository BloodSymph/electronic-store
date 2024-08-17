package com.company.review.service.admin.implementation;

import com.company.review.dto.admin.ReviewAdminResponse;
import com.company.review.exception.exceptions.ReviewNotFoundException;
import com.company.review.exception.exceptions.ReviewVersionNotValidException;
import com.company.review.mapper.admin.ReviewAdminMapper;
import com.company.review.repository.ReviewRepository;
import com.company.review.service.admin.ReviewAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.review.util.CacheEvictUtility.evictAllCaches;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"reviews_admin"})
public class ReviewAdminServiceImpl implements ReviewAdminService {

    private final ReviewRepository reviewRepository;

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ReviewAdminResponse> getAllReviews(Pageable pageable) {
        return reviewRepository
                .findAll(pageable)
                .map(ReviewAdminMapper::mapToReviewAdminResponse);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public Page<ReviewAdminResponse> searchReviews(
            Pageable pageable, String productTitle) {
        return reviewRepository
                .findByProductTitleIgnoreCase(
                        pageable, productTitle
                ).map(ReviewAdminMapper::mapToReviewAdminResponse);
    }

    @Override
    @Transactional
    public void deleteReviewByProfileId(Long profileId, Long reviewVersion) {
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

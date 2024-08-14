package com.company.review.mapper.admin;

import com.company.review.dto.admin.ReviewAdminResponse;
import com.company.review.entity.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewAdminMapper {

    public static ReviewAdminResponse mapToReviewAdminResponse(ReviewEntity review) {
        return ReviewAdminResponse.builder()
                .id(review.getId())
                .profileId(review.getProfileId())
                .title(review.getTitle())
                .comment(review.getComment())
                .rate(review.getRate())
                .created(review.getCreated())
                .updated(review.getUpdated())
                .version(review.getVersion())
                .build();
    }

}

package com.company.review.mapper.client;

import com.company.review.dto.client.ReviewClientRequest;
import com.company.review.dto.client.ReviewClientResponse;
import com.company.review.entity.ReviewEntity;
import org.springframework.stereotype.Component;


@Component
public class ReviewClientMapper {

    public static ReviewClientResponse mapToReviewClientResponse(ReviewEntity review) {
        return ReviewClientResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .comment(review.getComment())
                .rate(review.getRate())
                .created(review.getCreated())
                .build();
    }

    public static ReviewEntity mapReviewClientRequestToEntity(ReviewClientRequest review) {
        return ReviewEntity.builder()
                .profileId(review.getProfileId())
                .comment(review.getComment())
                .rate(review.getRate())
                .version(review.getVersion())
                .build();
    }

}

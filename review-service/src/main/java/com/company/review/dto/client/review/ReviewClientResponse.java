package com.company.review.dto.client.review;

import com.company.review.dto.client.rating.RatingClientResponse;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewClientResponse {

    private Long id;

    private Long profileId;

    private String comment;

    private RatingClientResponse rate;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime created;

}

package com.company.review.dto.client.rating;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingClientResponse {

    private Long id;

    private Integer rate;

}

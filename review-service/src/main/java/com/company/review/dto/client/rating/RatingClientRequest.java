package com.company.review.dto.client.rating;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingClientRequest {

    @Range(max = 1, message = "Rating field shod have maximum of {max} characters!")
    private Integer rate;

    @NotNull(message = "Rating version field shod not contains null value!")
    private Long version;

}

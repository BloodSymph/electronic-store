package com.company.review.dto.client;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewClientRequest {

    @NotNull(message = "User profile id field shod not contains null value!")
    private Long profileId;

    @Length(max = 2000, message = "Review comment field shod have maximum of {max} characters!")
    private String comment;

    @Range(max = 1, message = "Rating field shod have maximum of {max} characters!")
    private Integer rate;

    @NotNull(message = "Review version field shod not contains null value!")
    private Long version;

}

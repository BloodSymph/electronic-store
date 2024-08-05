package com.company.review.dto.client.review;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


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

    @NotNull(message = "Review version field shod not contains null value!")
    private Long version;

}

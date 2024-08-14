package com.company.review.dto.client;

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

    private String title;

    private String comment;

    private Double rate;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime created;

}

package com.company.review.controller.client;

import com.company.review.service.client.ReviewClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review-service/client")
public class ReviewClientController {

    private final ReviewClientService reviewClientService;

}

package com.company.review.service.client.implementation;

import com.company.review.repository.ReviewRepository;
import com.company.review.service.client.ReviewClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewClientServiceImpl implements ReviewClientService {

    private final ReviewRepository reviewRepository;

    private final RatingRepository ratingRepository;


}

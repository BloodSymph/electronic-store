package com.company.review.controller.client;

import com.company.review.dto.client.ReviewClientRequest;
import com.company.review.dto.client.ReviewClientResponse;
import com.company.review.service.client.ReviewClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review-service/client")
public class ReviewClientController {

    private final ReviewClientService reviewClientService;

    @GetMapping("/{productId}/reviews")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewClientResponse> getAllReviews(
            @PageableDefault(
                    sort = "rate",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable,
            @PathVariable(value = "productId") Long productId) {
        return reviewClientService.getAllReviews(
                pageable, productId
        );
    }

    @GetMapping("/{productId}/summary-rating")
    @ResponseStatus(HttpStatus.OK)
    public Double getSummaryProductRating(
            @PathVariable(value = "productId") Long productId) {
        return reviewClientService.getSummaryRateOfProduct(productId);
    }

    @PostMapping("/{productUrl}/add-review")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewClientResponse addReview(
            @Valid @RequestBody ReviewClientRequest reviewClientRequest,
            @PathVariable(value = "productUrl") String productUrl) {
        return reviewClientService.addReview(
                reviewClientRequest, productUrl
        );
    }

    @DeleteMapping("/{profileId}/review/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReview(
            @PathVariable(value = "profileId") Long profileId,
            @RequestParam(value = "reviewVersion") Long reviewVersion){
        reviewClientService.deleteReview(profileId, reviewVersion);
        return new ResponseEntity<>(
                "Review successful removed by profileId: " + profileId + " !",
                HttpStatus.OK
        );
    }

}

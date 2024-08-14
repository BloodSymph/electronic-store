package com.company.review.controller.admin;

import com.company.review.dto.admin.ReviewAdminResponse;
import com.company.review.dto.client.ReviewClientRequest;
import com.company.review.dto.client.ReviewClientResponse;
import com.company.review.service.admin.ReviewAdminService;
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
@RequestMapping("/api/v1/review-service/admin")
public class ReviewAdminController {

    private final ReviewAdminService reviewAdminService;

    @GetMapping("/reviews")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewAdminResponse> getAllReviews(
            @PageableDefault(
                    sort = "rate",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable) {
        return reviewAdminService.getAllReviews(pageable);
    }

    @DeleteMapping("/{profileId}/review/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReview(
            @PathVariable(value = "profileId") Long profileId,
            @RequestParam(value = "reviewVersion") Long reviewVersion){
        reviewAdminService.deleteReviewByProfileId(profileId, reviewVersion);
        return new ResponseEntity<>(
                "Review successful removed by profileId: " + profileId + " !",
                HttpStatus.OK
        );
    }

}

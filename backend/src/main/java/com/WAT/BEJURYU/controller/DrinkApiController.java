package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.ReviewResponses;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.service.DrinkService;
import com.WAT.BEJURYU.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/drinks")
public class DrinkApiController {

    private final DrinkService drinkService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<DrinkResponses> findAll() {
        final DrinkResponses drinks = drinkService.getAllDrinks();

        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DrinkResponses> findByName(@PathVariable String name) {
        final DrinkResponses drinks = drinkService.getDrinksByName(name);

        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/{drink_id}/reviews")
    public ResponseEntity<ReviewResponses> findReviewsById(@PathVariable(value = "drink_id") Long drinkId) {
        final ReviewResponses reviews = reviewService.getReviews(drinkId);

        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{drink_id}/reviews")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable(value = "drink_id") Long drinkId,
                                                       @RequestBody WriteReviewRequest reviewRequest) {
        final ReviewResponse review = drinkService.postReview(drinkId, reviewRequest);

        return ResponseEntity.ok(review);
    }

    @PutMapping("/{drink_id}/reviews/{review_id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable(value = "drink_id") Long drinkId,
                                                       @PathVariable(value = "review_id") Long reviewId,
                                                       @RequestBody WriteReviewRequest reviewRequest) {
        final ReviewResponse review = drinkService.updateReview(reviewId, reviewRequest);

        return ResponseEntity.ok(review);
    }
}

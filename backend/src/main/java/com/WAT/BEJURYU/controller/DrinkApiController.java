package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.DrinkRankingResponse;
import com.WAT.BEJURYU.dto.DrinkRatingResponse;
import com.WAT.BEJURYU.dto.DrinkResponse;
import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.dto.DrinkWithRatingResponse;
import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.ReviewResponses;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.service.DrinkService;
import com.WAT.BEJURYU.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<DrinkResponse> findById(@PathVariable Long id) {
        final DrinkResponse drink = drinkService.getDrinkById(id);

        return ResponseEntity.ok(drink);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DrinkResponses> findByName(@PathVariable String name) {
        final DrinkResponses drinks = drinkService.getDrinksByName(name);

        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<DrinkResponses> findByType(@PathVariable DrinkType type) {
        final DrinkResponses foundByType = drinkService.getDrinksByType(type);

        return ResponseEntity.ok(foundByType);
    }

    @GetMapping("/rankings/rating")
    public ResponseEntity<DrinkRankingResponse> findTop10ByRating() {
        final List<DrinkWithRatingResponse> drinks = drinkService.findTop10ByRating();

        return ResponseEntity.ok(new DrinkRankingResponse(drinks));
    }

    @GetMapping("/rankings/review")
    public ResponseEntity<DrinkRankingResponse> findTop10ByReviews() {
        final List<DrinkWithRatingResponse> drinks = drinkService.findTop10ByReviews();

        return ResponseEntity.ok(new DrinkRankingResponse(drinks));
    }

    @GetMapping("/{drink_id}/rating")
    public ResponseEntity<DrinkRatingResponse> findRating(@PathVariable(value = "drink_id") Long id) {
        final double averageScore = reviewService.getAverageScore(id);
        final DrinkRatingResponse result = new DrinkRatingResponse(id, averageScore);

        return ResponseEntity.ok(result);
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

    @GetMapping(value = "/{drink_name}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> showImage(@PathVariable("drink_name") final String name) {
        final DrinkResponses drinksByName = drinkService.getDrinksByName(name.replaceAll("_", " "));

        return ResponseEntity.ok(drinksByName.getDrinks().get(0).getImage());
    }
}

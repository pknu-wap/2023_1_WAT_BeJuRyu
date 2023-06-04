package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.*;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.service.DrinkService;
import com.WAT.BEJURYU.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        final DrinkResponses drinks = drinkService.getAllDrinks();
        final List<DrinkResponse> foundByName = drinks.getDrinks().stream()
                .filter(drink -> drink.getName().contains(name))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DrinkResponses(foundByName));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<DrinkResponses> findByType(@PathVariable DrinkType type) {
        final DrinkResponses foundByType = drinkService.getDrinksByType(type);

        return ResponseEntity.ok(foundByType);
    }

    @GetMapping("/rankings/rating")
    public ResponseEntity<DrinkRankingResponse> findTop10ByRating() {
        final List<DrinkResponse> drinks = getDrinksByRating();
        Collections.reverse(drinks);
        final List<DrinkWithRatingResponse> ranking = drinks.subList(0,10).stream()
                .map(d -> DrinkWithRatingResponse.from(d,reviewService.getAverageScore(d.getId()).getRating(),reviewService.getReviewSize(d.getId())))
                .filter(d-> !Double.isNaN(d.getRating()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DrinkRankingResponse(ranking));
    }

    /*private List<DrinkResponse> getDrinksByRating() {
        final DrinkResponses drinks = drinkService.getAllDrinks();
        return drinks.getDrinks().stream()
                .sorted((drink1, drink2) -> (int) (reviewService.getAverageScore(drink2.getId()).getRating() - reviewService.getAverageScore(drink1.getId()).getRating()))
                .collect(Collectors.toList());
    }*/

    private List<DrinkResponse> getDrinksByRating() {
        final DrinkResponses drinks = drinkService.getAllDrinks();
        return drinks.getDrinks().stream()
                .sorted(Comparator.comparing(d -> reviewService.getAverageScore(d.getId()).getRating()))
                .collect(Collectors.toList());
    }

    @GetMapping("/rankings/review")
    public ResponseEntity<DrinkRankingResponse> findTop10ByReviews() {
        final List<DrinkResponse> drinks = getDrinksByReviews();
        final List<DrinkWithRatingResponse> ranking = drinks.subList(0,10).stream()
                .map(d -> DrinkWithRatingResponse.from(d,reviewService.getAverageScore(d.getId()).getRating(),reviewService.getReviewSize(d.getId())))
                .filter(d-> reviewService.getReviewSize(d.getId())>0)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DrinkRankingResponse(ranking));
    }

    private List<DrinkResponse> getDrinksByReviews() {
        final DrinkResponses drinks = drinkService.getAllDrinks();
        return drinks.getDrinks().stream()
                .sorted((drink1, drink2) -> reviewService.getReviewSize(drink2.getId()) - reviewService.getReviewSize(drink1.getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{drink_id}/rating")
    public ResponseEntity<DrinkRatingResponse> findRating(@PathVariable(value = "drink_id") Long id) {
        final DrinkRatingResponse rating = reviewService.getAverageScore(id);
        return ResponseEntity.ok(rating);
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

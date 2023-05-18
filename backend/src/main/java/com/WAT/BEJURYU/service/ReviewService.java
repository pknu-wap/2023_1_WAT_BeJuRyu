package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.ReviewResponses;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Review;
import com.WAT.BEJURYU.repository.DrinkRepository;
import com.WAT.BEJURYU.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository){this.reviewRepository=reviewRepository;}

    public ReviewResponses getReviews(long drink_id){
        final List<Review> reviews = reviewRepository.findByDrinkId(drink_id);
        return ReviewResponses.of(reviews);
    }

    @Transactional
    public ReviewResponse postReview(ReviewResponse reviewResponse){
        Review review = Review.builder()
            .id(reviewResponse.getId())
            .comment(reviewResponse.getComment())
            .score(reviewResponse.getScore())
            .user(reviewResponse.getUser())
            .date(reviewResponse.getDate())
            .drink(reviewResponse.getDrink())
            .build();

        Review postedReview = reviewRepository.save(review);

        return ReviewResponse.from(postedReview);
    }
}

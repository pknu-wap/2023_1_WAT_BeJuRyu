package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkRatingResponse;
import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.ReviewResponses;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Review;
import com.WAT.BEJURYU.repository.MemberRepository;
import com.WAT.BEJURYU.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public ReviewService(ReviewRepository reviewRepository, final MemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
    }

    public ReviewResponses getReviews(long drinkId) {
        final List<Review> reviews = reviewRepository.findByDrinkId(drinkId);

        return ReviewResponses.of(reviews);
    }

    public int getReviewSize(long drinkId) {
        return reviewRepository.countByDrinkId(drinkId);
    }

    public DrinkRatingResponse getAverageScore(long drinkId) {
        final List<Review> reviews = reviewRepository.findByDrinkId(drinkId);
        double sum = reviews.stream()
                .mapToDouble(Review::getScore)
                .sum();
        double result = Math.round((sum / (double)reviews.size()) * 100) / 100.0;

        return new DrinkRatingResponse(drinkId,result);
    }

    @Transactional
    public ReviewResponse postReview(Drink drink, WriteReviewRequest reviewRequest) {
        final Member member = memberRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Review review = Review.builder()
                .comment(reviewRequest.getComment())
                .score(reviewRequest.getScore())
                .user(member)
                .date(reviewRequest.getDate())
                .drink(drink)
                .build();

        Review postedReview = reviewRepository.save(review);
        return ReviewResponse.from(postedReview);
    }

    @Transactional
    public ReviewResponse updateReview(final Long reviewId, WriteReviewRequest reviewRequest) {
        final Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        review.updateScore(reviewRequest.getScore());
        review.updateComment(reviewRequest.getComment());
        review.updateDate(reviewRequest.getDate());

        return ReviewResponse.from(review);
    }
}

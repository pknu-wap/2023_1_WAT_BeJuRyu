package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.ReviewResponses;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Review;
import com.WAT.BEJURYU.repository.DrinkRepository;
import com.WAT.BEJURYU.repository.MemberRepository;
import com.WAT.BEJURYU.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final DrinkRepository drinkRepository;

    public ReviewService(ReviewRepository reviewRepository, final MemberRepository memberRepository, final DrinkRepository drinkRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
        this.drinkRepository = drinkRepository;
    }

    public ReviewResponses getReviews(long drinkId) {
        final String name = drinkRepository.findById(drinkId).get().getName();

        return ReviewResponses.of(getReviewsByDrinkName(name));
    }

    public List<Review> getReviewsByDrinkName(final String name) {
        return reviewRepository.findByDrinkName(name).stream()
                .sorted(comparing(Review::getDate).reversed())
                .collect(Collectors.toList());
    }

    public int getReviewSize(long drinkId) {
        return reviewRepository.countByDrinkId(drinkId);
    }

    public double getAverageScore(long drinkId) {
        final List<Review> reviews = reviewRepository.findByDrinkId(drinkId);
        double sum = reviews.stream()
                .mapToDouble(Review::getScore)
                .sum();
        return Math.round(sum / (double) reviews.size() * 100) / 100.0;
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

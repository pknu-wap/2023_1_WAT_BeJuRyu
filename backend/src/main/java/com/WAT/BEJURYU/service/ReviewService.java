package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.ReviewResponses;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Review;
import com.WAT.BEJURYU.repository.MemberRepository;
import com.WAT.BEJURYU.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private MemberRepository memberRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewResponses getReviews(long drink_id) {
        final List<Review> reviews = reviewRepository.findByDrinkId(drink_id);
        return ReviewResponses.of(reviews);
    }

    @Transactional
    public ReviewResponse postReview(Drink drink, WriteReviewRequest reviewRequest) {
        Optional<Member> member = memberRepository.findById(reviewRequest.getUser_id());
        Review review = Review.builder()
            .comment(reviewRequest.getComment())
            .score(reviewRequest.getScore())
            .user(member.get())
            .date(reviewRequest.getDate())
            .drink(drink)
            .build();

        Review postedReview = reviewRepository.save(review);

        return ReviewResponse.from(postedReview);
    }
}

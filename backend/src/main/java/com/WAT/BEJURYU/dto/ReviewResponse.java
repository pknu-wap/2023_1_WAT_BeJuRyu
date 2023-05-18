package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewResponse {
    private final Long id;
    private final String comment;
    private final int score;
    private final String date;
    private final Member user;
    private final Drink drink;
    public static ReviewResponse from(final Review review) {
        return new ReviewResponse(review.getId(),
            review.getComment(),
            review.getScore(),
            review.getDate(),
            review.getUser(),
            review.getDrink());
    }

}

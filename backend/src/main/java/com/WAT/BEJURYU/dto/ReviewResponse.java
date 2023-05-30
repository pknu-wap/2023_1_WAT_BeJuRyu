package com.WAT.BEJURYU.dto;

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
    private final String nickname;
    private final long drink_id;

    public static ReviewResponse from(final Review review) {
        return new ReviewResponse(review.getId(),
            review.getComment(),
            review.getScore(),
            review.getDate(),
            review.getUser().getNickname(),
            review.getDrink().getId());
    }

}

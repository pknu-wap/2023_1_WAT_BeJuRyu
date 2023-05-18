package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Review;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ReviewResponses {
    private final List<ReviewResponse> reviews;

    public static ReviewResponses of(final List<Review> reviews) {
        final List<ReviewResponse> result = reviews.stream()
            .map(ReviewResponse::from)
            .toList();

        return new ReviewResponses(result);
    }
}

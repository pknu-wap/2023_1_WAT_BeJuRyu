package com.WAT.BEJURYU.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
public class WriteReviewRequest {

    private long userId;
    private String comment;
    private int score;
    private String date ;

    public WriteReviewRequest(final long userId, final String comment, final int score,final String date) {
        this.userId = userId;
        this.comment = comment;
        this.score = score;
        this.date = date;
    }
}

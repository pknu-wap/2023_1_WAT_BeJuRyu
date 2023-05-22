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
    private String date = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    public WriteReviewRequest(final long userId, final String comment, final int score) {
        this.userId = userId;
        this.comment = comment;
        this.score = score;
    }
}

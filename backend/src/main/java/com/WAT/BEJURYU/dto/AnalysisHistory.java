package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Sentiment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public final class AnalysisHistory {
    private final Long id;
    private final LocalDateTime date;
    private final Sentiment sentiment;

    public static AnalysisHistory from(final AnalysisResponse analysis) {
        return new AnalysisHistory(analysis.getId(), analysis.getDate(), analysis.getSentiment());
    }

}

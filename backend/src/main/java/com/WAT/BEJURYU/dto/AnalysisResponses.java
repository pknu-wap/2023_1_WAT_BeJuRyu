package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Analysis;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AnalysisResponses {

    private final List<LocalDateTime> analysisDate;

    public static AnalysisResponses of(final List<Analysis> analyze) {
        final List<LocalDateTime> result = analyze.stream()
            .map(d -> d.getDate())
            .toList();

        return new AnalysisResponses(result);
    }

}

package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Analysis;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AnalysisResponses {

    private final List<AnalysisResponse> analyze;

    public static AnalysisResponses of(final List<Analysis> analyze) {
        final List<AnalysisResponse> result = analyze.stream()
            .map(AnalysisResponse::from)
            .toList();

        return new AnalysisResponses(result);
    }

}

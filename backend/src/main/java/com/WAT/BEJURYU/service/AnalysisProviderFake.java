package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Sentiment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AnalysisProviderFake implements AnalysisProvider {
    @Override
    public Sentiment analyze(AnalysisSourceRequest request) {
        final Sentiment[] sentiments = Sentiment.values();
        Arrays.sort(sentiments);

        return sentiments[0];
    }
}

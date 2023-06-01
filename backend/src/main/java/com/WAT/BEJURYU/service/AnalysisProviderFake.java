package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Sentiment;
import org.springframework.stereotype.Component;

@Component
public class AnalysisProviderFake implements AnalysisProvider{
    @Override
    public Sentiment analyze(AnalysisSourceRequest request) {
        return Sentiment.HAPPY_1;
    }
}

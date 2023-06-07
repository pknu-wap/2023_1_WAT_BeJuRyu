package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Sentiment;

import java.io.IOException;

public interface AnalysisProvider {
    Sentiment analyze(AnalysisSourceRequest request) throws IOException;
}

package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Sentiment;

public interface AnalysisProvider {
    Sentiment analyze(AnalysisSourceRequest request);
}

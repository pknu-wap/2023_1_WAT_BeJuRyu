package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisResponse;
import com.WAT.BEJURYU.dto.AnalysisResponses;
import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.repository.AnalysisRepository;
import com.WAT.BEJURYU.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnalysisService {

    private final MemberRepository memberRepository;
    private final AnalysisRepository analysisRepository;

    public AnalysisService(AnalysisRepository analysisRepository,
        final MemberRepository memberRepository) {
        this.analysisRepository = analysisRepository;
        this.memberRepository = memberRepository;
    }

    public AnalysisResponses getAnalyze(long userId) {
        final List<Analysis> analyze = analysisRepository.findByMemberId(userId);

        return AnalysisResponses.of(analyze);
    }

    public AnalysisResponse getAnalysis(long Id) {
        final Analysis analysis = analysisRepository.findById(Id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 정보입니다."));

        return AnalysisResponse.from(analysis);
    }

    @Transactional
    public AnalysisResponse postAnalysis(AnalysisSourceRequest sourceRequest) {
        final Member member = memberRepository.findById(sourceRequest.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Analysis analysis = Analysis.builder()
            .member(member)
            .textExpression(sourceRequest.getTextExpression())
            .facialExpression(sourceRequest.getFacialExpression())
            .date(sourceRequest.getDate())
            .build();

        Analysis postedAnalysis = analysisRepository.save(analysis);

        return AnalysisResponse.from(postedAnalysis);
    }

}

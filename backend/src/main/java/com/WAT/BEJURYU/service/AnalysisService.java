package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisResponse;
import com.WAT.BEJURYU.dto.AnalysisResponses;
import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Sentiment;
import com.WAT.BEJURYU.repository.AnalysisRepository;
import com.WAT.BEJURYU.repository.DrinkRepository;
import com.WAT.BEJURYU.repository.MemberRepository;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnalysisService {

    private final MemberRepository memberRepository;
    private final AnalysisRepository analysisRepository;
    private final DrinkRepository drinkRepository;
    private final AnalysisProvider analysisProvider;

    public AnalysisService(AnalysisRepository analysisRepository,
                           final MemberRepository memberRepository,
                           DrinkRepository drinkRepository, final AnalysisProvider analysisProvider) {
        this.analysisRepository = analysisRepository;
        this.memberRepository = memberRepository;
        this.drinkRepository = drinkRepository;
        this.analysisProvider = analysisProvider;
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
    public AnalysisResponse postAnalysis(final Long userId, final AnalysisSourceRequest sourceRequest) {
        final Member member = memberRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        final Analysis analysis = getAnalysis(sourceRequest, member);
        Analysis persistedAnalysis = analysisRepository.save(analysis);

        return AnalysisResponse.from(persistedAnalysis);
    }

    private Analysis getAnalysis(AnalysisSourceRequest sourceRequest, Member member) {
        final Sentiment sentiment = analysisProvider.analyze(sourceRequest);
        final List<Drink> drinks = drinkRepository.findBySentiment(sentiment);
        Collections.shuffle(drinks);
        final Drink recommended = drinks.get(0);

        return Analysis.builder()
                .sentiment(sentiment)
                .date(sourceRequest.getDate())
                .member(member)
                .facialExpression(sourceRequest.getFacialExpression())
                .textExpression(sourceRequest.getTextExpression())
                .recommendDrink(recommended)
                .build();
    }
}

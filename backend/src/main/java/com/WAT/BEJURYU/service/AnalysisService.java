package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisHistory;
import com.WAT.BEJURYU.dto.AnalysisResponse;
import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Sentiment;
import com.WAT.BEJURYU.repository.AnalysisRepository;
import com.WAT.BEJURYU.repository.DrinkRepository;
import com.WAT.BEJURYU.repository.MemberRepository;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<AnalysisHistory> findHistoriesByUserId(long userId) {
        return analysisRepository.findByMemberId(userId).stream()
                .map(AnalysisHistory::from)
                .collect(Collectors.toList());
    }

    public AnalysisResponse findAnalysisById(long Id) {
        final Analysis analysis = analysisRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 감정 분석 결과입니다."));

        return AnalysisResponse.from(analysis);
    }

    @Transactional
    public AnalysisResponse postAnalysis(final Long userId, final AnalysisSourceRequest sourceRequest) throws IOException {
        final Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (sourceRequest.isNotExistExpressions()) {
            throw new IllegalArgumentException("텍스트, 사진 모두 필요합니다.");
        }

        try {
            final Analysis analysis = analyze(sourceRequest, member);
            Analysis persistedAnalysis = analysisRepository.save(analysis);

            return AnalysisResponse.from(persistedAnalysis);
        } catch (final IOException e) {
            throw new IllegalArgumentException("분석 요청 중 문제가 발생했습니다.");
        } catch (final JSONException e) {
            throw new IllegalArgumentException("얼굴을 찾을 수 없습니다.");
        }
    }

    private Analysis analyze(AnalysisSourceRequest sourceRequest, Member member) throws IOException {
        final Sentiment sentiment = analysisProvider.analyze(sourceRequest);
        final Drink drink = getRecommendedDrink(sentiment);

        return Analysis.builder()
                .sentiment(sentiment)
                .date(sourceRequest.getDate())
                .member(member)
                .facialExpression(sourceRequest.getFacialExpression())
                .textExpression(sourceRequest.getTextExpression())
                .recommendDrink(drink)
                .build();
    }

    private Drink getRecommendedDrink(final Sentiment sentiment) {
        final List<Drink> drinks = drinkRepository.findBySentiment(sentiment);
        Collections.shuffle(drinks);

        return drinks.get(0);
    }
}

package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Transactional
public class AnalysisRepositoryTest {
    @Autowired
    private AnalysisRepository analysisTestRepository;
    @Autowired
    private MemberRepository userTestRepository;

    private Member member;

    @BeforeEach
    public void before() {
        member = Member.builder()
                .id(12312412L)
                .nickname("퀸원지")
                .build();
        userTestRepository.save(member);
    }

    @Test
    void 유저_ID로_조회_테스트() {
        // given
        Analysis analysis = Analysis.builder()
                .member(member)
                .build();
        analysisTestRepository.save(analysis);

        // when
        List<Analysis> findUserAnalysis = analysisTestRepository.findByMemberId(member.getId());

        // then
        assertThat(findUserAnalysis.get(0).getMember().getId()).isEqualTo(member.getId());
    }

    @Test
    void 작성시간_범위로_조회_테스트() {
        // given
        String str1 = "2023-05-01 13:47:13.248";
        String str2 = "2023-05-03 13:47:13.248";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);

        Analysis analysis1 = Analysis.builder()
                .member(member)
                .date(dateTime1)
                .build();
        Analysis analysis2 = Analysis.builder()
                .member(member)
                .date(dateTime2)
                .build();

        analysisTestRepository.save(analysis1);
        analysisTestRepository.save(analysis2);

        // when
        LocalDateTime start = LocalDateTime.parse("2023-05-01 00:00:00.000", formatter);
        LocalDateTime end = LocalDateTime.parse("2023-05-01 23:59:59.999", formatter);
        List<Analysis> findUserAnalysis = analysisTestRepository.findByDateBetween(start, end);

        // then
        assertThat(findUserAnalysis.get(0).getId()).isEqualTo(analysis1.getId());
    }
}

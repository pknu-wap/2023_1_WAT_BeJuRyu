package com.WAT.BEJURYU.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.User;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
public class AnalysisRepositoryTest {
    @Autowired
    private AnalysisRepository analysisTestRepository;
    @Autowired
    private UserRepository userTestRepository;

    private User user;

    @BeforeEach
    public void before(){
         user = User.builder()
            .nickname("퀸원지")
            .build();
        userTestRepository.save(user);
    }
    @Test
    void 유저_ID로_조회_테스트() {
        // given
        Analysis analysis = Analysis.builder()
            .user(user)
            .build();
        analysisTestRepository.save(analysis);

        // when
        List<Analysis> findUserAnalysis = analysisTestRepository.findByUserId(user.getId());

        // then
        assertThat(findUserAnalysis.get(0).getUser().getId()).isEqualTo(user.getId());
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
            .user(user)
            .date(dateTime1)
            .build();
        Analysis analysis2 = Analysis.builder()
            .user(user)
            .date(dateTime2)
            .build();

        analysisTestRepository.save(analysis1);
        analysisTestRepository.save(analysis2);

        // when
        LocalDateTime start = LocalDateTime.parse("2023-05-01 00:00:00.000",formatter);
        LocalDateTime end = LocalDateTime.parse("2023-05-01 23:59:59.999",formatter);
        List<Analysis> findUserAnalysis = analysisTestRepository.findByDateBetween(start,end);

        // then
        assertThat(findUserAnalysis.get(0).getId()).isEqualTo(analysis1.getId());
    }
}

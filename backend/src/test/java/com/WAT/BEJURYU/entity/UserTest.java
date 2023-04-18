package com.WAT.BEJURYU.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Test
    public void 빌더_테스트(){
        //given
        String nickname = "퀸원지";

        //when
        User user = User.builder()
            .nickname(nickname)
            .build();

        //then
        assertThat(user.getNickname()).isEqualTo(nickname);
    }

    @Test
    public void 댓글_추가(){
        //given
        User user = new User();
        Review testReview = new Review();

        //when
        user.addReview(testReview);
        user.addReview(testReview);
        user.addReview(testReview);

        //then
        assertThat(user.getReviews().size()).isEqualTo(3);
    }

    @Test
    public void 분석_추가(){
        //given
        User user = new User();
        Analysis analysis = new Analysis();

        //when
        user.addAnalysis(analysis);

        //then
        assertThat(user.getReviews().size()).isEqualTo(1);
    }

    @Test
    public void 댓글_목록_조회(){
        //given
        User user = new User();
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = new ArrayList<>(Arrays.asList(review1,review2));

        //when
        user.addReview(review1);
        user.addReview(review2);

        //then
        assertThat(user.getReviews()).isEqualTo(reviews);
    }

    @Test
    public void 분석_목록_조회(){
        //given
        User user = new User();
        Analysis analysis1 = new Analysis();
        Analysis analysis2 = new Analysis();
        List<Analysis> analyses = new ArrayList<>(Arrays.asList(analysis1,analysis2));

        //when
        user.addAnalysis(analysis1);
        user.addAnalysis(analysis2);

        //then
        assertThat(user.getAnalyses()).isEqualTo(analyses);
    }


}

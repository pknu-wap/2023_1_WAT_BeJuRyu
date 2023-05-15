package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 닉네임으로_조회_테스트() {
        // given
        Member user = Member.builder()
                .id(1L)
                .nickname("퀸원지")
                .build();
        Member savedUser = memberRepository.save(user);

        // when
        List<Member> findUsers = memberRepository.findByNickname(savedUser.getNickname());

        // then
        assertThat(findUsers.get(0).getNickname()).isEqualTo("퀸원지");
        assertThat(findUsers.get(0).getId()).isNotNull();
    }
}

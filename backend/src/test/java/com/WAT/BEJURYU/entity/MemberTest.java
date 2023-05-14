package com.WAT.BEJURYU.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemberTest {

    @Test
    public void 빌더_테스트() {
        //given
        String nickname = "퀸원지";

        //when
        Member member = Member.builder()
                .nickname(nickname)
                .build();

        //then
        assertThat(member.getNickname()).isEqualTo(nickname);
    }
}

package com.WAT.BEJURYU.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
}

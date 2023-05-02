package com.WAT.BEJURYU.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.WAT.BEJURYU.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userTestRepository;

    @Test
    void 닉네임으로_조회_테스트() {
        // given
        User user = User.builder()
            .nickname("퀸원지")
            .build();
        User savedUser = userTestRepository.save(user);

        // when
        List<User> findUsers = userTestRepository.findByNickname(savedUser.getNickname());

        // then
        assertThat(findUsers.get(0).getNickname()).isEqualTo("퀸원지");
        assertThat(findUsers.get(0).getId()).isNotNull();
    }
}

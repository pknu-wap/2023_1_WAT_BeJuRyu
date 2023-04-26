package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByNickname(String nickname);
}

package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNickname(String nickname);
}

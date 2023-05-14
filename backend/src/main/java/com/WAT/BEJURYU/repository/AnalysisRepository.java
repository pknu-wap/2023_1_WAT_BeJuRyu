package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    List<Analysis> findByMemberId(Long memberId);

    List<Analysis> findByDateBetween(LocalDateTime start, LocalDateTime end);

}

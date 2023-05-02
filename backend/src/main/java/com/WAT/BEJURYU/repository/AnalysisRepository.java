package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Analysis;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis,Long> {
    List<Analysis> findByUserId(Long user_id);
    List<Analysis> findByDateBetween(LocalDateTime start, LocalDateTime end);

}

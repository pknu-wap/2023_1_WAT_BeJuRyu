package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
    List<Review> findByUserId(Long user_id);
    List<Review> findByDrinkId(Long drink_id);


}

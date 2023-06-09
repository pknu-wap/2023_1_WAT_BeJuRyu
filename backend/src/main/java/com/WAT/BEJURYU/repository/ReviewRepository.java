package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDrinkId(Long drinkId);

    List<Review> findByDrinkName(String drinkName);

    int countByDrinkId(Long drinkId);
}

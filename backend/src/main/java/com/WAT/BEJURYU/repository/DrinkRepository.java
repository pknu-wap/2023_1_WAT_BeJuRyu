package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.entity.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByPriceBetween(int lowPrice, int highPrice);

    List<Drink> findByPriceGreaterThan(int price);

    List<Drink> findByPriceLessThan(int price);

    List<Drink> findByName(String name);

    List<Drink> findBySentiment(Sentiment sentiment);

    List<Drink> findByType(DrinkType type);
}

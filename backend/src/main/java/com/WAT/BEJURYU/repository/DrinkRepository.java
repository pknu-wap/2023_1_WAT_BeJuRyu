package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Drink;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink,Long> {
    List<Drink> findByPriceBetween(int lowPrice,int highPrice);
    List<Drink> findByPriceGreaterThan(int price);
    List<Drink> findByPriceLessThen(int price);
    List<Drink> findByType(String type);


}

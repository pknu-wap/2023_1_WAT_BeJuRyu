package com.WAT.BEJURYU.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.WAT.BEJURYU.entity.Drink;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class DrinkRepositoryTest {
    @Autowired
    private DrinkRepository drinkRepository;

    @Test
    void 가격_범위로_조회_테스트() {
        // given
        Drink drink1 = Drink.builder()
            .name("데낄라")
            .price(1000)
            .build();
        Drink drink2 = Drink.builder()
            .name("테라")
            .price(500)
            .build();
        drinkRepository.save(drink1);
        drinkRepository.save(drink2);

        // when
        List<Drink> drinks = drinkRepository.findByPriceBetween(0,800);


        // then
        assertThat(drinks.get(0).getName()).isEqualTo("테라");
    }
}

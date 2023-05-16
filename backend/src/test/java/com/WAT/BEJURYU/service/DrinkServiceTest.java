package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.repository.DrinkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DrinkServiceTest {
    @Mock
    DrinkRepository drinkRepository;

    @InjectMocks
    private DrinkService testService;

    @DisplayName("전체 주류 목록을 조회할 수 있다 - 상태검증")
    @Test
    void findAll1() {
        //given
        Drink drink = Drink.builder()
                .name("데낄라")
                .dosu(35)
                .price(35000)
                .type(DrinkType.LIQUEUR)
                .build();

        final List<Drink> expected = List.of(drink);
        given(drinkRepository.findAll()).willReturn(expected);

        //when
        final DrinkResponses drinks = testService.getAllDrinks();

        //then
        assertThat(drinks.getDrinks()).hasSize(1);
    }

    @DisplayName("전체 주류 목록을 조회할 수 있다 - 행위검증")
    @Test
    void findAll2() {
        //when
        testService.getAllDrinks();

        //then
        verify(drinkRepository, times(1)).findAll();
    }
}

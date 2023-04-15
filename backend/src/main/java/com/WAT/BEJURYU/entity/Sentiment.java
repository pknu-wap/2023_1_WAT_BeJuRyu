package com.WAT.BEJURYU.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Sentiment {
    @Id
    private Long id;
    private SentimentType sentimentType;
    private int degree;
    private List<Drink> drinks;

}

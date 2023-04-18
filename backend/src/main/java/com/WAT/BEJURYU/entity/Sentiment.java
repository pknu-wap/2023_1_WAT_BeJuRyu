package com.WAT.BEJURYU.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Sentiment {
    @Id
    @Column(name="type")
    private SentimentType sentimentType;

    @OneToMany(mappedBy = "sentiment")
    @JoinColumn(name="drink_id")
    private List<Drink> drinks;
}

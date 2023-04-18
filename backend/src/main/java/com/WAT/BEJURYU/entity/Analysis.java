package com.WAT.BEJURYU.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="text_expresstion")
    private String textExpression;
    @Column(name="facial_expression")
    private String facialExpression;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sentiment_id")
    private Sentiment sentiment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drink_id")
    private Drink recommendDrink;
}

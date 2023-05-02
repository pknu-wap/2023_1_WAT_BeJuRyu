package com.WAT.BEJURYU.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="text_expresstion")
    private String textExpression;
    @Column(name="facial_expression")
    private String facialExpression;

    @Column(name="date")
    private LocalDateTime date;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sentiment_id")
    private Sentiment sentiment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drink_id")
    private Drink recommendDrink;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

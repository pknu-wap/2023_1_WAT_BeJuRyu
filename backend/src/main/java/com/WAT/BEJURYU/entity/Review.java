package com.WAT.BEJURYU.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "score")
    private int score;
    @Column(name = "date")
    private String date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member user;
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;
}

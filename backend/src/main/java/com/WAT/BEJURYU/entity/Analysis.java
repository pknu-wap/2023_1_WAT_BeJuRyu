package com.WAT.BEJURYU.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text_expresstion")
    private String textExpression;
    @Lob
    @Column(name = "facial_expression", columnDefinition = "LONGBLOB")
    private byte[] facialExpression;

    @Column(name = "date")
    private LocalDateTime date;
    @Enumerated(value = EnumType.STRING)
    private Sentiment sentiment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private Drink recommendDrink;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;
}

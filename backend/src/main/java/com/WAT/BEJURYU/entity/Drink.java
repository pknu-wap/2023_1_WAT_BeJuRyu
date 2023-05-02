package com.WAT.BEJURYU.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drink {
    @Id
    @Column(name="drink_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="bottle_color")
    private String bottleColor;
    @Column(name="type")
    @Enumerated(value = EnumType.STRING)
    private DrinkType type;
    @Column(name="dosu")
    private double dosu;
    @Column(name="sweetness")
    private int sweetness;
    @Column(name="price")
    private int price;
    @Column(name="volume")
    private int volume;
    @OneToMany(mappedBy = "drink")
    private List<Review> reviews;
    @ManyToOne
    @JoinColumn(name ="sentiment_id")
    private Sentiment sentiment;

}


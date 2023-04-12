package com.WAT.BEJURYU.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public final class User {

    @Id
    @Column(name = "user_id")
    private Long id;
    private String nickname;
}

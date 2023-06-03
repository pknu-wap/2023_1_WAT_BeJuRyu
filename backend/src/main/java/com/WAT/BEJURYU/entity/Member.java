package com.WAT.BEJURYU.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @Column(name = "member_id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;

    public void updateNickname(final String newNickname) {
        this.nickname = newNickname;
    }

}

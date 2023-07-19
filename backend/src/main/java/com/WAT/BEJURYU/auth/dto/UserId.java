package com.WAT.BEJURYU.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public final class UserId {
    private Long id;

    public Long get() {
        return id;
    }
}


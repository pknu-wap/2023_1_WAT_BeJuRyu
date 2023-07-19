package com.WAT.BEJURYU.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class LoginResponse {
    private final MemberResponse memberResponse;
    private final Token token;
}

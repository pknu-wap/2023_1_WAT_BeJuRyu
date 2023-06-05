package com.WAT.BEJURYU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberChangeNicknameRequest {

    private Long userId;
    private String newNickname;
}

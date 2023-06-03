package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResponse {
    final Long userId;
    final String nickname;

    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(),
                member.getNickname()
        );
    }
}

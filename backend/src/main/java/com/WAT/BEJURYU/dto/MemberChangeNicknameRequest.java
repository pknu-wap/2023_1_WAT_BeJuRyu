package com.WAT.BEJURYU.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Getter
@Setter
public class MemberChangeNicknameRequest {

    private Long userId;
    private String newNickname;

    public AnalysisSourceRequest( final Long userId,final String newNickname) {
        this.userId= userId;
        this.newNickname = newNickname;
    }
}

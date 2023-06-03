package com.WAT.BEJURYU.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Getter
public class MemberChangeNicknameResponse {
    private String newNickname;

    public AnalysisSourceResponse(final String newNickname) {
        this.newNickname = newNickname;
    }
}

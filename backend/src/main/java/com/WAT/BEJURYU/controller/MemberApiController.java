package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.auth.config.AuthParam;
import com.WAT.BEJURYU.auth.dto.MemberResponse;
import com.WAT.BEJURYU.auth.dto.UserId;
import com.WAT.BEJURYU.dto.MemberChangeNicknameRequest;
import com.WAT.BEJURYU.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;

    @PutMapping("/nickname")
    public ResponseEntity<MemberResponse> updateNickname(@AuthParam UserId userId, @RequestBody MemberChangeNicknameRequest memberChangeNicknameRequest) {
        if (userId.get().equals(memberChangeNicknameRequest.getUserId())) {
            final MemberResponse member = memberService.updateNickname(memberChangeNicknameRequest);
            return ResponseEntity.ok(member);
        }

        throw new IllegalArgumentException("잘못된 요청 정보입니다.");
    }

    @GetMapping("")
    public ResponseEntity<MemberResponse> findMember(@AuthParam UserId userId) {
        final MemberResponse member = memberService.getMember(userId.get());
        return ResponseEntity.ok(member);
    }

}

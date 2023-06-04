package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.auth.config.AuthParam;
import com.WAT.BEJURYU.auth.dto.MemberResponse;
import com.WAT.BEJURYU.auth.dto.UserId;
import com.WAT.BEJURYU.dto.*;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.service.DrinkService;
import com.WAT.BEJURYU.service.MemberService;
import com.WAT.BEJURYU.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;
    @PutMapping("/nickname")
    public ResponseEntity<MemberResponse> updateNickname(@AuthParam UserId userId, @RequestBody MemberChangeNicknameRequest memberChangeNicknameRequest) {
        final MemberResponse member = memberService.updateNickname(memberChangeNicknameRequest);

        return ResponseEntity.ok(member);
    }

    @GetMapping("")
    public ResponseEntity<MemberResponse> findMember(@AuthParam UserId userId) {
        final MemberResponse member = memberService.getMember(userId.get());
        return ResponseEntity.ok(member);
    }

}

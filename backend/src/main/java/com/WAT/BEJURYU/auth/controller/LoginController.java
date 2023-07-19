package com.WAT.BEJURYU.auth.controller;

import com.WAT.BEJURYU.auth.config.AuthParam;
import com.WAT.BEJURYU.auth.dto.*;
import com.WAT.BEJURYU.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public final class LoginController {
    private final LoginService loginService;

    @GetMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestParam("token") String kakaoToken) throws MalformedURLException, URISyntaxException {
        final KakaoUserInfo userInfo = loginService.parse(kakaoToken);

        if (loginService.isNewUser(userInfo.getId())) {
            loginService.register(userInfo);
        }

        final MemberResponse member = loginService.findMemberById(userInfo.getId());
        final Token token = loginService.createToken(userInfo);

        return ResponseEntity.ok(new LoginResponse(member, token));
    }

    /**
     * 토큰이 만료되면 바디에 아래와 같이 실어서 보낸다.(이때는 Bearer을 붙이지 않는다)
     * {
     * "access": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzM0OTYyMzgwIiwiaWF0IjoxNjgzNDQ3MTMxLCJleHAiOjE2ODM0NTQzMzF9.V16ATqEv-v5mns9-i6XE686kCA3MTern1B7JVsjgp9Y",
     * "refresh": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODM0NDcxMzEsImV4cCI6MTY4NDA1MTkzMX0.KA9cLJ4uBINVvIsQzwv-w8QtrMe8yxpjU4caYWKdTLs"
     * }
     */
    @PostMapping("/auth/refresh")
    public ResponseEntity<Token> refresh(@RequestBody Token token) {
        final Token reissuedToken = loginService.reissueToken(token);

        return ResponseEntity.ok(reissuedToken);
    }

    /**
     * 인증이 필요한 API에 아래와 같이 @AuthParam 어노테이션과 함께 UserId 클래스로 받도록 하면 된다.
     * <p>
     * 헤더에 "Authorization":"Bearer 토큰어쩌고" 정보가 없으면 400(Bad Request)을 리턴한다.
     * 만료된 토큰을 가져오면 401(Unauthorized)을 리턴한다.
     */
    @GetMapping("/test")
    public ResponseEntity<MemberResponse> test(@AuthParam UserId userId) {
        final MemberResponse member = loginService.findMemberById(userId.get());

        return ResponseEntity.ok(member);
    }
}

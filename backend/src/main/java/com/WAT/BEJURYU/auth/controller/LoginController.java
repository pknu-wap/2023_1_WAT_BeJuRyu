package com.WAT.BEJURYU.auth.controller;

import com.WAT.BEJURYU.auth.dto.KakaoUserInfo;
import com.WAT.BEJURYU.auth.dto.Token;
import com.WAT.BEJURYU.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public final class LoginController {
    private final LoginService loginService;

    @GetMapping("/auth/login")
    public ResponseEntity<Token> login(@RequestParam String token) throws MalformedURLException, URISyntaxException, ParseException {
        final KakaoUserInfo userInfo = loginService.parse(token);
        if (loginService.isNewUser(userInfo.getId())) {
            loginService.register(userInfo);
        }

        return ResponseEntity.ok(loginService.createToken(userInfo));
    }
}

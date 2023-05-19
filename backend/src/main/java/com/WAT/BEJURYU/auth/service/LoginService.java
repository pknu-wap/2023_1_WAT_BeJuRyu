package com.WAT.BEJURYU.auth.service;

import com.WAT.BEJURYU.auth.dto.KakaoUserInfo;
import com.WAT.BEJURYU.auth.dto.MemberResponse;
import com.WAT.BEJURYU.auth.dto.Token;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private static final String KAKAO_API_PROFILE = "https://kapi.kakao.com/v2/user/me";

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    public KakaoUserInfo parse(final String token) throws MalformedURLException, URISyntaxException {
        final RestTemplate client = new RestTemplate();
        final URL url = new URL(KAKAO_API_PROFILE);

        final HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);
        header.setAccessControlAllowCredentials(true);
        final HttpEntity<Object> httpEntity = new HttpEntity<>(header);

        return client.exchange(url.toURI(), HttpMethod.GET, httpEntity, KakaoUserInfo.class).getBody();
    }

    @Transactional
    public void register(final KakaoUserInfo userInfo) {
        final Member user = new Member(userInfo.getId(), userInfo.getProperties().getNickname());

        memberService.save(user);
    }

    public Token createToken(final KakaoUserInfo userInfo) {
        final String access = jwtTokenProvider.createToken(String.valueOf(userInfo.getId()));
        final String refresh = jwtTokenProvider.createRefreshToken();

        return new Token(access, refresh);
    }

    public Token reissueToken(final Token token) {
        jwtTokenProvider.validate(token.getRefresh());

        final String memberId = jwtTokenProvider.getPayload(token.getAccess());
        return new Token(jwtTokenProvider.createToken(memberId), null);
    }

    public boolean isNewUser(final Long id) {
        return memberService.notExistById(id);
    }

    public MemberResponse findMemberById(final Long id) {
        final Member member = memberService.findById(id);

        return MemberResponse.from(member);
    }
}

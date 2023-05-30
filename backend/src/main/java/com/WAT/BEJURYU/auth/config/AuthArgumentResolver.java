package com.WAT.BEJURYU.auth.config;

import com.WAT.BEJURYU.auth.dto.UserId;
import com.WAT.BEJURYU.auth.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public final class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "BEARER ";

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        if (parameter.getParameterType().equals(UserId.class)) {
            return parameter.hasParameterAnnotation(AuthParam.class);
        }

        return false;
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        final String auth = webRequest.getHeader(AUTHORIZATION);
        if (auth == null || !auth.toUpperCase().startsWith(BEARER)) {
            throw new IllegalArgumentException("인증 정보가 없습니다.");
        }

        final String accessToken = auth.substring(BEARER.length());
        jwtTokenProvider.validate(accessToken);

        final long userId = Long.parseLong(jwtTokenProvider.getPayload(accessToken));
        return new UserId(userId);
    }
}

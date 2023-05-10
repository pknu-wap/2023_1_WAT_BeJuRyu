package com.WAT.BEJURYU.auth.service;

import com.WAT.BEJURYU.auth.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public final class JwtTokenProvider {
    private static final String INVALID_TOKEN = "유효하지 않은 인증 정보입니다.";

    private final SecretKey key;
    private final long accessTokenExpired;
    private final long refreshTokenExpired;

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") final String secretKey,
                            @Value("${security.jwt.token.access-expired}") final long accessTokenExpired,
                            @Value("${security.jwt.token.refresh-expired}") final long refreshTokenExpired) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpired = accessTokenExpired;
        this.refreshTokenExpired = refreshTokenExpired;
    }

    public String createToken(final String payload) {
        final Date validity = new Date(System.currentTimeMillis() + accessTokenExpired);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken() {
        final Date validity = new Date(System.currentTimeMillis() + refreshTokenExpired);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getPayload(final String token) {
        return tokenToJws(token).getBody().getSubject();
    }

    public void validate(final String token) {
        validateExpiredToken(tokenToJws(token));
    }

    private Jws<Claims> tokenToJws(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (final Exception e) {
            throw new InvalidTokenException(INVALID_TOKEN);
        }
    }

    private void validateExpiredToken(final Jws<Claims> claims) {
        if (claims.getBody().getExpiration().before(new Date())) {
            throw new InvalidTokenException(INVALID_TOKEN);
        }
    }
}

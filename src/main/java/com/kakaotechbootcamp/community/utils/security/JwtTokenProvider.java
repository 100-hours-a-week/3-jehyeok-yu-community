package com.kakaotechbootcamp.community.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String USER_NAME = "username";
    private final Key key;
    private final long accessTtlMillis;
    private final Clock clock;

    public JwtTokenProvider(
        @Value("${security.jwt.secret}") String secret,
        @Value("${security.jwt.access-ttl-millis}") long accessTtlMillis,
        Clock clock
    ) {
        byte[] bytes = secret.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        if (bytes.length < 32) {
            throw new IllegalArgumentException("시크릿 키 32글자 검증");
        }
        this.key = io.jsonwebtoken.security.Keys.hmacShaKeyFor(bytes);
        this.accessTtlMillis = accessTtlMillis;
        this.clock = clock;
    }

    public String createAccessToken(Long userId, String username) {
        Instant now = Instant.now(clock);
        return Jwts.builder()
            .setSubject(String.valueOf(userId))
            .claim(USER_NAME, username)
            .setIssuedAt(Date.from(now))
            .setExpiration(new Date(now.toEpochMilli() + accessTtlMillis))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public Long userId(String token) {
        return Long.valueOf(parse(token).getBody().getSubject());
    }

    public String username(String token) {
        return parse(token).getBody().get(USER_NAME, String.class);
    }
}
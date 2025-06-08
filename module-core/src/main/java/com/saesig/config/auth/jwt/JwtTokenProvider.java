package com.saesig.config.auth.jwt;

import com.saesig.config.auth.formLogin.CustomUserDetailsService;
import com.saesig.global.service.CacheService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final CacheService cacheService;
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.access-expire-second-ms}")
    private long accessTokenExpireMs;
    @Value("${jwt.refresh-expire-second-ms}")
    private long refreshTokenExpireMs;

    public TokenResponseDto createToken(String username, List<String> roles) {
        String accessToken = createAccessToken(username, roles);
        String refreshToken = creatRefreshToken();

        cacheService.deleteRefreshToken(username);
        cacheService.saveRefreshToken(username, refreshToken);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String createAccessToken(String username, List<String> roles) {
        Claims claims = Jwts.claims()
                .add("roles", roles)
                .build();

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessTokenExpireMs);

        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public TokenResponseDto updateToken(String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return createToken(username, roles);
    }

    private String creatRefreshToken() {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshTokenExpireMs);

        return Jwts.builder()
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public List<String> getRoles(String token) {
        List<?> rawList = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles", List.class);

        return rawList.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws =
                    Jwts.parser()
                            .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                            .build()
                            .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            log.error(e.getMessage());
            return false;
        }
    }

}

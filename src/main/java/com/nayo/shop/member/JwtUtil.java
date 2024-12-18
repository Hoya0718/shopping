package com.nayo.shop.member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class JwtUtil {

    static final SecretKey key =
            Keys.hmacShaKeyFor(Decoders.BASE64.decode(
                    "jwtpassword123jwtpassword123jwtpassword123jwtpassword123jwtpassword" //키 털리면 안됨
            ));

    // JWT 만들어주는 함수
    public static String createToken(Authentication auth) { //jwt 만들어주는 함수
        //.claim(이름, 값)으로 jwt에 데이터 추가 가능 jwt 정보는 누구나 볼 수 있기 때문에 민간함 정보x 꼭 필요한 것만
        var user =(CustomUser) auth.getPrincipal();

        var authorities = auth.getAuthorities().stream().map(a -> a.getAuthority())
                .collect(Collectors.joining(","));
        String jwt = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("displayName", user.displayName)
                .claim("authorities", authorities)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 10000)) //유효기간 10초
                .signWith(key) //해싱할 때 넣을 비번
                .compact();
        return jwt;
    }

    // JWT 까주는 함수
    public static Claims extractToken(String token) { //jwt를 파라미터로 입력하면 문자로 반환하는 함수
        Claims claims = Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload();
        return claims;
    }
}

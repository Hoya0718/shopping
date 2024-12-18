package com.nayo.shop.member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        //1.쿠키 제출
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            filterChain.doFilter(request, response); //다음 필터 진행시켜
            return;
        }
        var jwtCookie = "";
        for(int i=0; i<cookies.length; i++) {
            if(cookies[i].getName().equals("jwt")) { //2. jwt 이름의 쿠키를 꺼낸다
                jwtCookie = cookies[i].getValue();
            }
        }
        System.out.println(jwtCookie);

        Claims claim;
        try{
            claim = JwtUtil.extractToken(jwtCookie); //jwt 값이 정상이면
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        var arr = claim.get("authorities").toString().split(","); //권한들을 List에 담아서 넣자
        var authorities = Arrays.stream(arr)
                .map(a -> new SimpleGrantedAuthority(a)).toList();

        var customUser = new CustomUser(
                claim.get("username").toString(),
                "none",
                authorities
        ); //auth변수에 넣는다
        customUser.displayName = claim.get("displayName").toString();

        var authToken = new UsernamePasswordAuthenticationToken(
                claim.get("username").toString(), ""
        );
        authToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        //요청들어올때마다 실행할코드~~
        filterChain.doFilter(request, response);
    }

}
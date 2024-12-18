package com.nayo.shop.member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/register")
    String join(Authentication auth) {
        if(auth != null) {
            return "redirect:/main";
        }
        return "register.html";
    }

    @PostMapping("/member")
    String addMember(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/main";
    }

    @GetMapping("/login")
    String login(Authentication auth){
        if(auth != null){
            return "redirect:/main";
        }
            return "/login.html";
    }

    @GetMapping("/my-page")
    String mypage(Authentication auth) { //유저 정보 볼수있는 오브젝트
        //System.out.println(auth);
        //System.out.println(auth.getName());
        //System.out.println(auth.isAuthenticated()); //auth가 Authentication의 시큐리티 객체인지 확인
        //System.out.println(auth.getAuthorities()); 권한 확인
        //System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저"))); 일반유저인지 확인
        return "mypage.html";
    }

    @PostMapping("/login/jwt")
    @ResponseBody
    public String loginJWT(@RequestBody Map<String, String> data,
                           HttpServletResponse response) {
        var authToken = new UsernamePasswordAuthenticationToken(
                data.get("username"), data.get("password")
        );
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken); //아이디/비번을 DB내용과 비교해 로그인 다르면 에러
        SecurityContextHolder.getContext().setAuthentication(auth);

        var jwt = JwtUtil.createToken(SecurityContextHolder.getContext().getAuthentication());
        //수동으로 로그인시 auth 변수에 유저정보 반영안됨
        System.out.println(jwt);

        //jwt를 쿠키에 저장
        var cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(10);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); //쿠키가 전송될 url
        response.addCookie(cookie);

        return jwt;
    }

    @GetMapping("/my-page/jwt")
    @ResponseBody
    String mypageJWT(Authentication auth) {

        var user =(CustomUser) auth.getPrincipal();
        System.out.println(user);
        System.out.println(user.displayName);
        System.out.println(user.getAuthorities());

        return "마이페이지데이터";
    }
}
